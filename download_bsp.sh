#!/bin/bash

set -euo pipefail

REPO="$HOME/bin/repo"
DEFAULT="$HOME/armada38x-bsp"
SOURCE="https://github.com/MarvellEmbeddedProcessors/yocto1.6-meta-armada38x"

echo "
Welcome to the Marvell Armada 38X BSP

"

read -p "Enter target directory for BSP (default: $DEFAULT): " TARGET
if [ -z "$TARGET" ]
then
  TARGET=$DEFAULT
fi
TARGET=$(eval echo $TARGET)

if [ ! -x $REPO ]; then
  echo "Installing repo to $REPO"
  mkdir -p $(dirname $REPO)
  curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > $REPO
  chmod a+x $REPO
fi

mkdir -p $TARGET
cd $TARGET
if [ -e $TARGET/.repo ]; then
  echo "$TARGET already exists, syncing"
  if $REPO sync; then
    echo "
Synched

To set up your Yocto environment, run the following commands:
  $ cd $TARGET
  $ MACHINE=388-db-gp . setup-environment build/
  $ bitbake core-image-base
"
    exit 0
  else
    echo "
repo sync error.  To reset, run the following:
$ rm -rf $TARGET/.repo

And then run this script again
"
    exit 1
  fi
fi

echo "
Installing BSP to $TARGET
Please wait, this may take several minutes......
"
if $REPO init -u $SOURCE -b manifest-yocto1.6-15t1
then
      $REPO sync
      echo -e "Done!  To make sure you have the necessary support packages installed, run the following command:
  $ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath libsdl1.2-dev xterm

To set up your Yocto environment, run the following commands:
  $ cd $TARGET
  $ MACHINE=388-db-gp . setup-environment build/
  $ bitbake core-image-base
"
else
	  rm -rf $TARGET/.repo
	  echo "Could not initialize the repository!  Please check that your internet connection is working, and that your Git identity has been set."
fi
