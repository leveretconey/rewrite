/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.recipes;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.yaml.Assertions.yaml;

class UpdateMovedRecipeYamlTest implements RewriteTest {

    @Test
    void changeYamlRecipeList() {
        rewriteRun(
          spec -> spec.recipe(new UpdateMovedRecipeYaml(
            "org.openrewrite.java.cleanup.AddSerialVersionUidToSerializable",
            "org.openrewrite.staticanalysis.AddSerialVersionUidToSerializable")),
          yaml("""
            type: specs.openrewrite.org/v1beta/recipe
            name: org.example.bank.Internal
            displayName: org.example.bank.Internal
            description: org.example.bank.Internal
            recipeList:
              - org.openrewrite.java.cleanup.AddSerialVersionUidToSerializable
              - org.openrewrite.java.cleanup.recipeUnmoved
            """,
            """
            type: specs.openrewrite.org/v1beta/recipe
            name: org.example.bank.Internal
            displayName: org.example.bank.Internal
            description: org.example.bank.Internal
            recipeList:
              - org.openrewrite.staticanalysis.AddSerialVersionUidToSerializable
              - org.openrewrite.java.cleanup.recipeUnmoved
            """));
    }
}
