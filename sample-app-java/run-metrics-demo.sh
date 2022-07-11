#!/bin/bash
## Runs metrics demo

echo "==== compiling ... === "
mvn package

echo "==== running ... === "
java -cp "target/*:target/dependency-jars/*"   com.elephantscale.metrics_demo.MetricsDemo

