#!/bin/sh

mvn -P prepareProject clean validate
mvn clean install
