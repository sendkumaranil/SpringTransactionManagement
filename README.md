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
