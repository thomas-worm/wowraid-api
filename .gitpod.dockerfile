FROM gitpod/workspace-postgres

RUN sudo apt-get update \
 && sudo apt-get install -y postgresql \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && sudo -u postgres createuser --superuser gitpod \
 && sudo -u postgres createuser --superuser zcatzlevtcsphw \
 && npm install -g npm

