DEPLOY_DIR=`pwd`
#echo $DEPLOY_DIR
LIB_DIR=$DEPLOY_DIR/lib
#echo $LIB_DIR
LIB_JARS=`ls $LIB_DIR | grep .jar | awk '{print "'$LIB_DIR'/"$0}' | tr '\n' ':'`
java -cp conf/:$LIB_JARS jd.pengfeng.supersecondfight.App $1
echo $1
