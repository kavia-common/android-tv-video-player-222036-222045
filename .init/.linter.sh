#!/bin/bash
cd /home/kavia/workspace/code-generation/android-tv-video-player-222036-222045/video_player_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

