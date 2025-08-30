#!/bin/bash

echo "Compiling Contact Vault..."
javac *.java

if [ $? -eq 0 ]; then
    echo
    echo "Compilation successful! Starting Contact Vault..."
    echo
    java ContactVault
else
    echo
    echo "Compilation failed! Please check your Java files for errors."
    read -p "Press Enter to continue..."
fi
