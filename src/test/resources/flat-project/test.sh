#!/bin/bash
rm -rf ~/.m2/repository/gui4j/
echo ##########################
../../../../mvnw -U clean jar-install:installjars
../../../../mvnw -U clean jar-install:installjars install
