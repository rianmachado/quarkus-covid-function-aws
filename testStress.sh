#!/bin/bash
for counter in $(seq 1 1000); do 
	echo "$counter";
	curl localhost:8080/reverseString -H "Content-Type: text/plain" -d "rian"
 done
