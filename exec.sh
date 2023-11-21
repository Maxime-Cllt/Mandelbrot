#!/bin/bash
clear
total_cores=$(nproc)
for ((i=0; i<total_cores; i++))
do
    taskset -c "$i" java client.Client &
done
wait
