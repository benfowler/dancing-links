#!/bin/bash

RECIPE=org.openrewrite.java.testing.junit5.JUnit5BestPractices

gradle rewriteRun --init-script init.gradle -Drewrite.activeRecipe="$RECIPE"

