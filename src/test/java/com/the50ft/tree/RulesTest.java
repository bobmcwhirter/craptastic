package com.the50ft.tree;

import java.util.concurrent.CountDownLatch;

import com.the50ft.tree.model.Aircraft;
import com.the50ft.tree.model.Checkout;
import com.the50ft.tree.model.Person;
import com.the50ft.tree.model.Request;
import com.the50ft.tree.rules.OrganizationUnit;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.RuleUnitExecutor;

/**
 * Created by bob on 6/30/17.
 */
public class RulesTest {

    @Test
    public void testMatch() throws InterruptedException {

        OrganizationUnit unit = new OrganizationUnit();

        Person bob = new Person("bob");
        unit.insertPerson(bob);
        Person keith = new Person("keith");
        unit.insertPerson(keith);
        Person al = new Person("al");
        unit.insertPerson(al);

        Aircraft n9858g = new Aircraft("N9858G");
        unit.insertAircraft(n9858g);
        Aircraft n666 = new Aircraft("N666");
        unit.insertAircraft(n666);

        Checkout c1 = new Checkout(bob, n9858g);
        unit.insertCheckout(c1);
        Checkout c2 = new Checkout(keith, n9858g);
        unit.insertCheckout(c2);
        Checkout c3 = new Checkout(keith, n666);
        unit.insertCheckout(c3);
        Checkout c4 = new Checkout(al, n9858g);
        unit.insertCheckout(c4);


        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieBase kBase = kContainer.getKieBase();

        RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kBase);
        executor.bind(kBase);

        Thread thread = new Thread(() -> {
            System.err.println( "running" );
            executor.runUntilHalt(unit);
        });

        thread.start();

        System.err.println( "insert");
        unit.insertRequest(new Request(bob));
        System.err.println( "inserted");

        Thread.sleep(5000);

        //executor.run(unit);

    }
}
