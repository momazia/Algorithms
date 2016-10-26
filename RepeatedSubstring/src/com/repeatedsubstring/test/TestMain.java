package com.repeatedsubstring.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.repeatedsubstring.main.Main;

public class TestMain {

	@Test
	public void test_findRepeatedString_1() {
		assertEquals("an", Main.findLongestRepeatedSubString("banana"));
	}

	@Test
	public void test_findRepeatedString_2() {
		assertEquals(Main.NONE, Main.findLongestRepeatedSubString("am so uniqe"));
	}

	@Test
	public void test_findRepeatedString_3() {
		assertEquals("qe ", Main.findLongestRepeatedSubString("aqe sqe "));
	}

	@Test
	public void test_findRepeatedString_4() {
		assertEquals("a", Main.findLongestRepeatedSubString("aqe  a    "));
	}

	@Test
	public void test_findRepeatedString_5() {
		assertEquals("aq", Main.findLongestRepeatedSubString("aqe  aq    "));
	}

	@Test
	public void test_findRepeatedString_6() {
		assertEquals("aqe ", Main.findLongestRepeatedSubString("aqe aqe    "));
	}

	@Test
	public void test_findRepeatedString_7() {
		assertEquals("abca", Main.findLongestRepeatedSubString("abcaabca"));
	}

	@Test
	public void test_findRepeatedString_8() {
		assertEquals("abc", Main.findLongestRepeatedSubString("abcabca"));
	}

	@Test
	public void test_findRepeatedString_9() {
		assertEquals("abc", Main.findLongestRepeatedSubString("abcaabc"));
	}

	@Test
	public void test_findRepeatedString_10() {
		assertEquals("m", Main.findLongestRepeatedSubString("iapmhvlhzbhlf  xlvmnwgoqjsf"));
	}
	
	@Test
	public void test_findRepeatedString_11() {
		assertEquals("mlhvtm", Main.findLongestRepeatedSubString("mlhvtmjymlhvtmrhctcego w ekwyurouyeqdu"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("ajusgcxknd  to vierbqf"));
		assertEquals("low ", Main.findLongestRepeatedSubString("A slow yellow fox crawls under the proactive dog"));
		assertEquals("nr", Main.findLongestRepeatedSubString("lnrvzifysyr sfpnrizs sjumrsdts"));
		assertEquals("xoom", Main.findLongestRepeatedSubString("fisx xoomy hwdmbdqvrxoomwtvvkcfy"));
		assertEquals("x", Main.findLongestRepeatedSubString("ixxu mvk   rrqctphxdhpnjdsoesx"));
		assertEquals("kx", Main.findLongestRepeatedSubString("lwakxzaz  uxx wfdcggzxgpbfqvgkxaxgl"));
		assertEquals("xmwccj", Main.findLongestRepeatedSubString("prwyqtftwxmwccjfjh jzykgbnfjavbmnwbkoxmwcclhmggxmwccjd"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("zbjqfgrwy v k xompsl"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("rxaksvj dpfqlybh ogwumen"));
		assertEquals("oh", Main.findLongestRepeatedSubString("tohqn mrxshade moholo ny uj fdmfimkijxqz"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("fpktlbwsru n zoed gcmh"));
		assertEquals("l", Main.findLongestRepeatedSubString("lzkojsxfi vbyhid uqlstckjpcticgymmcp"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("shcnmiextydr wa o kz"));
		assertEquals("urm", Main.findLongestRepeatedSubString("hradtrfzkwje bc spyurmvevurmackeg"));
		assertEquals("jil", Main.findLongestRepeatedSubString("tm jilks ywdk stfmamcxp ocjilcmc cnipx"));
		assertEquals("cyxnhp", Main.findLongestRepeatedSubString("otsjk bgewcyxnhpfvejbdxvvy dtjecyxnhpbtby uk"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("lwqtx ybzjkhif rusvagd"));
		assertEquals("mbpns", Main.findLongestRepeatedSubString("guweki mbpnsqkmbpnscikuwqhthxqnhzmbpnslvdyfandk jnf"));
		assertEquals("m", Main.findLongestRepeatedSubString("mzedvo dyolpj b  ggsmhrqva u"));
		assertEquals("k", Main.findLongestRepeatedSubString("ank dukv w ewuhs  lw"));
		assertEquals("hvvj", Main.findLongestRepeatedSubString("wtlphvvj oqmlthvvjp emnbhuqkhvvjj"));
		assertEquals("btke", Main.findLongestRepeatedSubString("btkerjbtkesobaejxnvf k idvuubtkezc"));
		assertEquals("ainq", Main.findLongestRepeatedSubString("pjnyyzainqywlkycemqbuainqainqfr fswpl tphz"));
		assertEquals("ubeby", Main.findLongestRepeatedSubString("mrzxtvjlp ubebysrwskecmxubebybwtbb"));
		assertEquals("he", Main.findLongestRepeatedSubString("hez tgdwvbl vhestluqimh efdn"));
		assertEquals("nivvg", Main.findLongestRepeatedSubString("qnrk qzaajexjannivvgqvjnivvgkqrcb"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("xemwtfo   viqncak"));
		assertEquals(" o ", Main.findLongestRepeatedSubString("a o  o a"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("A quick bown fx jmps vr the lazy dg"));
		assertEquals("srfo", Main.findLongestRepeatedSubString("vi psrfodeboesrfocaypvklisrfomzzzdh"));
		assertEquals("m", Main.findLongestRepeatedSubString("iapmhvlhzbhlf  xlvmnwgoqjsf"));
		assertEquals("p", Main.findLongestRepeatedSubString("pxqegtquxpmbltuthlgh"));
		assertEquals("jcl", Main.findLongestRepeatedSubString("lozjju eljclprohy qbdppa jzo ujclrbwdjcllqcs"));
		assertEquals("ossjb", Main.findLongestRepeatedSubString("pbbosfiyoossjblgfzcqgittyzossjbzyubp"));
		assertEquals("xfea", Main.findLongestRepeatedSubString("nxfeaglbww hms bczfowd vbmr oxfeakerrxc"));
		assertEquals("oj", Main.findLongestRepeatedSubString("i hojimraeps x zojoaxehdyz w dghp"));
		assertEquals("NONE", Main.findLongestRepeatedSubString("e t kb duvxzimfqhrpcoga"));
		assertEquals("qxy", Main.findLongestRepeatedSubString("qxy xpzxnmldmegfdjjhqetcdsvcyqxy"));
		assertEquals("m u", Main.findLongestRepeatedSubString("m u m ujiiramg"));
	}
}
