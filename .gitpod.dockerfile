FROM gitpod/workspace-postgres

ENV DATABASE_URL=postgresql://gitpod@127.0.0.1:5432/postgres

RUN sudo apt-get update \
 && sudo apt-get install -y postgresql postgresql-contrib libpq-dev \
 && sudo service postgresql start \
 && sudo sudo -u postgres createuser --superuser gitpod \
 && sudo sudo -u postgres createuser --superuser zcatzlevtcsphw \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && sudo sed -i '1s/.*/#!\/usr\/bin\/env bash'"\n"'unset PGHOSTADDR/' /usr/local/lib/heroku/bin/heroku \
 && npm install -g npm
