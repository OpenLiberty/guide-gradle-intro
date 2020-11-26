#!/bin/bash
while getopts t:d:b:u: flag;
do
    case "${flag}" in
        t) DATE="${OPTARG}";;
        d) DRIVER="${OPTARG}";;
        b) BUILD="${OPTARG}";;
        u) DOCKER_USERNAME="${OPTARG}";;
    esac
done

sed -i "\#// end::buildscript[]#a liberty { install { runtimeUrl=\"https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/nightly/"$DATE"/"$DRIVER"\"}}" build.gradle
cat build.gradle

../scripts/testApp.sh
