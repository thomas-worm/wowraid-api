FROM gitpod/workspace-postgres

ENV DATABASE_URL=postgresql://gitpod@127.0.0.1:5432/postgres

RUN sudo apt-get update \
 && sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common \
 && (curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -) \
 && sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu  $(lsb_release -cs)  stable" \
 && sudo apt-get update \
 && sudo apt-get install -y sshpass docker-ce postgresql postgresql-contrib libpq-dev \
 && sudo service postgresql start \
 && sudo sudo -u postgres createuser --superuser gitpod \
 && sudo sudo -u postgres createuser --superuser zcatzlevtcsphw \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && sudo sed -i '1s/.*/#!\/usr\/bin\/env bash'"\n"'unset PGHOSTADDR/' /usr/local/lib/heroku/bin/heroku \
 && npm install -g npm
