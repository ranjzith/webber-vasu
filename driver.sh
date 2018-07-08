#!/bin/bash
SCRIPT=$1
PROCESS_PATH=$2
CID=$3

if [[ ! -d $PROCESS_PATH/inprocess ]]; then
  mkdir $PROCESS_PATH/inprocess
fi

if [[ ! -d $PROCESS_PATH/completed ]]; then
  mkdir $PROCESS_PATH/completed
fi

if [[ ! -d $PROCESS_PATH/failed ]]; then
  mkdir $PROCESS_PATH/failed
fi

echo "sh $SCRIPT $4 $5 $6 $7 $8 $9 $10 $11 $12 $13" > $PROCESS_PATH/inprocess/$CID 2>&1
sh $SCRIPT $4 $5 $6 $7 $8 $9 $10 $11 $12 $13 >> $PROCESS_PATH/inprocess/$CID 2>&1

if [[ "$?" == 0 ]]; then
  mv $PROCESS_PATH/inprocess/$CID $PROCESS_PATH/completed/$CID
else
  mv $PROCESS_PATH/inprocess/$CID $PROCESS_PATH/failed/$CID
fi