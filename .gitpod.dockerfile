FROM gitpod/workspace-postgres

RUN sudo apt-get update \
 && sudo apt-get install -y postgresql postgresql-contrib libpq-dev \
 && sudo cat /etc/sudoers \
 && sudo -u postgres createuser --superuser gitpod \
 && sudo -u postgres createuser --superuser zcatzlevtcsphw \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && npm install -g npm

