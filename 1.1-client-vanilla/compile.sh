#!/bin/sh
cp *.java ../../src/minecraft/net/minecraft/src/
pushd ../..
./recompile.sh
./reobfuscate.sh
popd

