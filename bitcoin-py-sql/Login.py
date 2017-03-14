# coding=UTF-8

HOST = "mcdf.asuscomm.com"
USER = "user"
PASS = "1234"
DBNAME = "test"
PORT = "3306"

import MySQLdb

try:
    db = MySQLdb.connect(HOST, USER, PASS, DBNAME, charset='utf8')
    cursor = db.cursor()
    cursor.execute("insert into user values ('df','dfdf')")
    db.close()

except MySQLdb.Error as e:
    print "Error %d: %s" % (e.args[0], e.args[1])
