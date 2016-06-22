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

```java
	TransactionStatus getTransaction(TransactionDefinition difinition) throws TransactionException
	
	void commit(TransactionStatus status) throws TransactionException
	
	void rollback(TransactionStatus status) throws TransactionException
	

	
