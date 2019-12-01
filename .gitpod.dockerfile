FROM gitpod/workspace-postgres

RUN sudo apt-get update \
 && sudi apt-get install -y postgresql
