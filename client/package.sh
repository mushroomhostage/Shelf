#!/bin/sh
OUT=shelves-fml125-r1.zip
./compile.sh
cp ../../reobf/minecraft/{BlockShelf.class,CraftingInventoryShelfCB.class,GuiShelf.class,ItemShelf.class,TileEntityShelf.class,TileEntityShelfRenderer.class,mod_Shelf.class} .
zip -r $OUT *.class 
