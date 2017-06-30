package com.the50ft.tree;

import java.util.ArrayList;
import java.util.List;

import com.the50ft.tree.model.Aircraft;
import com.the50ft.tree.model.Checkout;
import com.the50ft.tree.model.Person;
import com.the50ft.tree.model.Requestor;
import com.the50ft.tree.resources.memory.InMemoryResourceCatalog;
import com.the50ft.tree.rules.DualInstruction;
import com.the50ft.tree.schedule.memory.InMemorySchedules;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.RuleUnitExecutor;

/**
 * Created by bob on 6/30/17.
 */
public class RulesTest {

    @Test
    public void testMatch() throws InterruptedException {

        InMemoryResourceCatalog catalog = new InMemoryResourceCatalog();

        Person bob = new Person("bob");
        catalog.add(Person.class, bob);
        Person keith = new Person("keith");
        catalog.add(Person.class, keith);
        Person al = new Person("al");
        catalog.add(Person.class, al);

        Aircraft n9858g = new Aircraft("N9858G");
        catalog.add(Aircraft.class, n9858g);
        Aircraft n666 = new Aircraft("N666");
        catalog.add(Aircraft.class, n666);

        Checkout c1 = new Checkout(bob, n9858g);
        Checkout c2 = new Checkout(keith, n9858g);
        Checkout c3 = new Checkout(keith, n666);
        Checkout c4 = new Checkout(al, n9858g);

        List<Checkout> checkouts = new ArrayList<>();
        checkouts.add(c1);
        checkouts.add(c2);
        checkouts.add(c3);
        checkouts.add(c4);

        Requestor requestor = new Requestor(bob);

        InMemorySchedules schedules = new InMemorySchedules();

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();

        KieBase kBase = kContainer.getKieBase();

        RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kBase);
        executor.bind(kBase);
        List list = new ArrayList();
        executor.bindVariable("list", list);
        executor.run(new DualInstruction(bob, schedules, catalog, checkouts));

        System.err.println("----- the list ---> " + list);



        /*
        KieSession kSession = kContainer.newKieSession();

        kSession.setGlobal("out", System.out);
        kSession.insert(requestor);
        kSession.insert(bob);
        kSession.insert(keith);
        kSession.insert(n9858g);
        kSession.insert(n666);
        kSession.insert(c1);
        kSession.insert(c2);
        kSession.insert(c3);
        kSession.fireAllRules();
        */


    }
}
