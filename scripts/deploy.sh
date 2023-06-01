#!/usr/bin/env bash

mvn clean install -U -P local

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/order-service-1.0.jar \
    kvn@172.26.0.62:/home/kvn/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa kvn@172.26.0.62 << EOF
pgrep java | xargs kill -9
nohup java -jar order-service-1.0.jar > log.txt &
EOF

echo 'Bye'

#!/usr/bin/env bash

mvn clean install -U -P local

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/demo-1.0.jar \
    kvn@172.26.0.62:/home/kvn/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa kvn@172.26.0.62 << EOF
pgrep java | xargs kill -9
nohup java -jar demo-1.0.jar > log.txt &
EOF

echo 'Bye'