#!/bin/sh
rm -rf net
mkdir -p net/minecraft/server
./compile.sh
cp *.class net/minecraft/server/
zip -r shelf-mcpc125-r1.zip net/
