# coding=UTF-8

import MySQLdb

try:
    # 建立DB 連線資訊定設定中文編碼utf-8
    db = MySQLdb.connect("127.0.0.1", "user01", "master3421", "test", charset = 'utf8')

except MySQLdb.Error as e:
    print "Error %d: %s" % (e.args[0], e.args[1])