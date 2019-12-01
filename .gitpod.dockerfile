FROM gitpod/workspace-postgres

RUN sudo apt-get update \
 && sudo apt-get install -y postgresql postgresql-contrib libpq-dev \
 && sudo service postgresql start \
 && sudo sudo -u postgres createuser --superuser gitpod \
 && sudo sudo -u postgres createuser --superuser zcatzlevtcsphw \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && npm install -g npm

