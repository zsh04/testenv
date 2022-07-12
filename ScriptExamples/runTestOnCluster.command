#!/bin/bash

# set Java CLASSPATH
export CLASSPATH=.:/Applications/ZebraTester/prxsniff.jar:/Applications/ZebraTester/iaik_jce_full.jar:/Applications/ZebraTester/iaik_ssl.jar:/Applications/ZebraTester/iaik_eccelerate.jar:/Applications/ZebraTester/iaikPkcs11Provider.jar:/Applications/ZebraTester

# add Java binaries to PATH
export PATH=/Applications/ZebraTester/jre/bin:$PATH

# change to directory of load test program
cd /Applications/ZebraTester/MyTests

# clear all data in analyse load test menu
java PdfReport clear

# loop over simulated users
# -------------------------
for users in 2 6 12 24
do

# define load test program. Note: if the program is zipped you have to add ".zip" to the program name
loadTestProgram="Test01"
loadTestProgramArgs="-u $users -d 30 -t 60 -sdelay 200 -maxloops 0 -sampling 15 -percpage 100 -percurl 20 -maxerrmem 20 -nolog"

# define cluster name
clusterName="clusterA"

# define load test result file
currentDate=`date "+%d%h%y_%H%M%S"`
loadTestResultFile="`echo $loadTestProgram`_`echo $currentDate`_`echo $users`u.prxres"

# create cluster job
java PrxJob -s transmitClusterJob "$clusterName" $loadTestProgram $loadTestProgramArgs
prxstat=`cat PRXSTAT`
if [ $prxstat -lt "0" ]; then
  echo "unable to define job, status = $prxstat"
  exit 1;
fi
jobId=$prxstat

# start load test job on cluster. Note: if an input file must be splitted you have to use the -split argument - see appication reference manual
java PrxJob -s startClusterJob "$clusterName" $jobId
prxstat=`cat PRXSTAT`
if [ $prxstat -ne "0" ]; then
  echo "unable to start job, status = $prxstat"
  exit 1;
fi
echo "$loadTestProgram started with $users users on $clusterName, job ID = $jobId"

# wait until job is completed
java PrxJob -s waitForClusterJobCompletion "$clusterName" $jobId
prxstat=`cat PRXSTAT`
if [ $prxstat -ne "0" ]; then
  echo "unable to wait for job $jobId, status = $prxstat"
  exit 1;
fi
echo "job ID = $jobId completed on $clusterName"

# acquire load test result file
java PrxJob -s acquireClusterJobResultFile "$clusterName" $jobId "$loadTestResultFile"
prxstat=`cat PRXSTAT`
if [ $prxstat -ne "0" ]; then
  echo "acquire load test result file, status = $prxstat"
  exit 1;
fi
echo "load test result $loadTestResultFile acquired"

# load result into analyse load test menu
java PdfReport load $loadTestResultFile

# end loop over simulated users
# -----------------------------
done



