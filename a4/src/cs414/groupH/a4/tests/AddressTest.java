package cs414.groupH.a4.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs414.groupH.a4.address.Address;

public class AddressTest {

	@Test
	public void test() {
		Address a = new Address("916 Timber", "Fort Collins", "CO", "80521", "9702085431");
	}

}
