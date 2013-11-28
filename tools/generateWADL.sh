#!/bin/bash
if [ $# -lt 1 ]; then
echo "You need give the project name."
else
wget http://localhost:8080/$(1)?_wadl -O $(1).wadl
fi
