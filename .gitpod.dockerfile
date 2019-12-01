FROM gitpod/workspace-postgres

RUN sudo apt-get update \
 && sudo apt-get install -y postgresql \
 && ( curl https://cli-assets.heroku.com/install.sh | sh ) \
 && npm install -g npm

