# SpringTransactionManagement
Spring Transaction Management Examples

Spring Transaction Management
------------------------------

<b>Transaction:</b><br>

    Begin -------------->[Transaction]
    						/\
    					   /  \
    				      /    \
    					 /      \
    	Transaction Succeed		Transaction Falied
    			|						|
    			|						|
    			|						|
    		  Commit				Rollback
		  

<b>ACID Properties:</b><br>
<ul>
	<li>
		A. Atomicity
	</li>
	<li>
		C. Consistency
	</li>
	<li>
		I. Isolation
	</li>
	<li>
		D. Durability
	</li>
</ul>

<b>Spring Transaction Management:</b><br>
<ul>
	<li>
		Supports programmatic and declarative transactions.
	</li>
	<li>
		Programmatic transaction management achieve via<br>
		PlatformTransactionManager <br>
		TransactionTemplate
	</li>
	<li>
		Declarative transaction management achieve via<br>
		Spring AOP <br>
		Annotation
	</li>
	<li>
		Supports many transaction properties:
		<ul>
			<li>Propagation</li>
			<li>Isolation level</li>
			<li>Rollback condition</li>
			<li>Read Only</li>
			<li>Timeout</li>
		</ul>
	</li>
	
</ul>

<b>Spring transaction Built-In Implementations:</b>

      
      
      															PlatformTransactionManager
      																		|
      														AbstractPlatformTransactionManager 
      																		|
      																		|
      	------------------------------------------------------------------------------------------------------------------------------------------
      	|						|						|								|								|						|
      	JtaTransactionManager	JpaTransactionManager	DataSourceTransactionManager	HibernateTransactionManager		JdoTransactionManager	JmsTransactionManager
	
<b>Spring transaction management API:</b><br>

<p>PlatformTransactionManager interface</p>

	TransactionStatus getTransaction(TransactionDefinition difinition) throws TransactionException
	
	void commit(TransactionStatus status) throws TransactionException
	
	void rollback(TransactionStatus status) throws TransactionException
	

	
<b>Programmatic Transaction Management:(Transactional Class)</b><br>

		import org.springframework.transaction.support.TransactionTemplate
		
		public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop{
			private TransactionTemplate transactionTemplate;
			
			public void setTransactionTemplate(TransactionTemplate transactionTemplate){
				this.transactionTemplate=transactionTemplate;
			}
			
			public void purchase(final String isbn,final String username){
				transactionTemplate.execute(new TransactionCallbackWithoutResult(){
					
					protected void doInTransactionWithoutResult(TransactionStatus status){
					
					.......
					}
				
				});
			}
		}

<b>Programmatic Transaction Management:(Bean Definition)</b><br>

		<beans>
			
			<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
				<property name="dataSource" ref="dataSource"/>
			</bean>
			<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
				<property name="transactionManager" ref="transactionManager"/>
			</bean>
			<bean id="bookShop" class="com.springexamples.TransactionalJdbcBookShop">
				<property name="transactionTemplate" ref="transactionTemplate"/>
			</bean>
		</beans>

<b>Declarative Transaction Management:</b><br>
<ul>
	<li>aop:config, aop:pointcut, aop:advisor</li>
	<li>tx:advice, tx:method, tx:attributes</li>
</ul>
<b>Annotative Transaction Management:</b><br>
<ul><li>@Transactional</li></ul>
<b>Transaction Attributes:</b><br>
<ul>
	<li>Propogation</li>
	<li>Read-Only</li>
	<li>timeout</li>
	<li>Isolation</li>
	<li>Rollback-rules</li>
</ul>
<b>Transaction Propogation Attributes:</b><br>
<ul>
	<li><b>REQUIRED:</b> If there is an existing transaction in progress, the current method should run within this transaction.
	otherwise, it should start a new transaction and run within its own transaction.</li>
	<li><b>REQUIRES_NEW:</b>The current method must start a new transaction and run within its own transaction. If there's an existing
	transaction in progress, it should be suspended.</li>
	<li><b>SUPPORTS:</b>If there's existing transaction progress, the current method can run within this transaction. otherwise, 
	it is not necessary to run within a transaction</li>
	<li><b>NOT_SUPPORTED:</b>The current method should not run within a transaction.If there's an existing transaction in progress, it should be suspended.</li>
	<li><b>MANDATORY:</b>The current method must run within a transaction.If there's no existing transaction in progress, an exception will trown.</li>
	<li><b>NEVER:</b>The current method should not run within a transaction. If there's an existing transaction in progress, exception will be thrown.</li>
	<li><b>NESTED:</b>If there's an existing transaction in progress,the current method should run within the nested transaction of the transaction.Otherwise it should
	start a new transaction and run within its own transaction.</li>
</ul>
<b>Transactional Isolation issues:</b><br>
<ul>
	<li>Lost Update</li>
	<li>Dirty Read</li>
	<li>Unrepeatable Read</li>
	<li>Second lost updates problem</li>
	<li>Phantom read</li>
</ul>
<b>Transactional Isolation Levels:</b><br>
<ul>
	<li><b>Read Uncommited:</b>Permits dirty reads but not lost updates. One transaction may not write to a row if another uncommited transaction
	has already written to it.This isolation level may be implemented using exclusive write lock.</li>
	<li><b>Read Committed:</b>Permits unrepeatable reads but not dirty reads.This may be achieved using momentary shared read locks and exclusive write locks.
	Reading transaction dont block other transaction from accessing row.However, an uncommitted  writing transaction blocks all other transaction from accessing a row.</li>
	<li><b>Repeatable Read:</b>Permits neither unrepeatable reads nor dirty reads.Phantom reads may occur.This may be achieved using shared reads lock and exclusive write lock
	Reading transaction block writing transaction and writing transaction blocks all other transaction.</li>
	<li><b>Serializable:</b>Provides the strictest transaction isolation.It emulates serial transaction execution.as if transaction had been executed one after
	another,serially, rather than concurrently.Serializablity may not implemented using only row-level locks</li>
</ul>
