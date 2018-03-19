FROM sebp/elk

# overwrite existing file
ADD ./02-beats-input.conf /etc/logstash/conf.d/02-beats-input.conf

#ENV ES_HOME /opt/elasticsearch
#WORKDIR ${ES_HOME}
#RUN CONF_DIR=/etc/elasticsearch gosu elasticsearch bin/elasticsearch-plugin \install ingest-geoip
#RUN CONF_DIR=/etc/elasticsearch gosu elasticsearch bin/elasticsearch-plugin \install  x-pack

#WORKDIR ${KIBANA_HOME}
#RUN gosu kibana bin/kibana-plugin install elastic/sense
#RUN CONF_DIR=/etc/elasticsearch gosu elasticsearch bin/elasticsearch-plugin \install  x-pack
#RUN gosu kibana bin/kibana-plugin install x-pack
