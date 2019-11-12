# springboot_transaction
JPA_事务并发测试

DEFAULT ：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是： READ_COMMITTED 。 
READ_UNCOMMITTED ：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。 
READ_COMMITTED ：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。 
REPEATABLE_READ ：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。 
SERIALIZABLE ：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。 

指定方法：通过使用 isolation 属性设置，例如：@Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)

使用默认最高的隔离级别，即可防止脏读取、重复读、幻读，但是这样同时也消耗系统性能。


本测试最主要的方法就是在查询出数据后，在更新这条数据userRepository.flush() 方法的使用，
    @Override
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public User findUserById(long id) {
        User byId = userRepository.findById(id);
        byId.setPassword("aaaaaaa");
        userRepository.save(byId);
//        userRepository.saveAndFlush(byId);
        userRepository.flush();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return byId;
    }
    
    不加flush();如果这是在重启一个会话查询，线程挂起，这时查询不会阻塞,因为save()方法执行完毕，客户端对实体中数据的改变和手写的SQL语句都保存在客户端内存中，
    当执行flush()后，对数据库的修改就发送到数据库服务器端的数据高速缓冲区，而不是数据库文件中。这时事务正在修改，查询阻塞。
    
   ORACLE:如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性； 
如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询SQL必须保证整体的读一致性，否则，在前条SQL查询之后，后条SQL查询之前，
数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，此时，应该启用事务支持。
