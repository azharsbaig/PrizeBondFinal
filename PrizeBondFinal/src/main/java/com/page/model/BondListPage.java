package com.page.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BondListPage {	
	WebDriver a;
	public BondListPage(WebDriver driver ) {
		a=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="(//*[text()='BUSINESS'])[2]")
	private WebElement business;
	
	@FindBy(xpath="//*[@class='menu_active']")
	private WebElement financeLink;
	
	@FindBy(xpath="(//*[text()='Prize Bond Results'])[2]")
	private WebElement PrizeBondList;
	
	@FindBy(xpath="(//*[@id='pe_close_btn']")
	private WebElement popup;
	
	@FindBy(xpath="(//*[text()='Prize Bond RS. 100/-'])[2]")
	private WebElement HPB;
	
	@FindBy(xpath="(//*[text()='Prize Bond RS. 200/-'])[2]")
	private WebElement THPB;
	
	@FindBy(xpath="(//*[text()='Prize Bond RS. 750/-'])[2]")
	private WebElement SFPB;
	
	@FindBy(xpath="(//*[text()='Prize Bond RS. 1,500/-'])[2]")
	private WebElement FHPB;

	@FindBy(xpath="(//*[text()='Prize Bond RS. 7,500/-'])[2]")
	private WebElement SFHPB;

	@FindBy(xpath="(//*[text()='Prize Bond RS. 15,000/-'])[2]")
	private WebElement FTPB;
	
	@FindBy(xpath="(//*[text()='Prize Bond RS. 25,000/-'])[2]")	
	private WebElement TFTPB;
	
	@FindBy(xpath="//*[text()='Prize Bond RS. 40,000/-'])[2]")
	private WebElement FTDPB;	

	
	@FindBy(xpath="(//table)[2]/tbody/tr")//rows
	private List<WebElement> tabrow;	
	
	@FindBy(xpath = "//input[@id='txtId']")
	private WebElement rowChange;
	
	@FindBy(xpath = "//*[@id='results']/table/tbody/tr/td[1]")
	private WebElement winMessage;
	
	@FindBy(xpath = "//*[@id='results']/table/tbody/tr/td[2]")
	private WebElement pValue;
	
		
	public WebElement madhuri(int i, int j) {
		WebElement x = a.findElement(By.xpath("(//table)[2]/tbody/tr[" + (i) + "]/td[" + (j) + "]"));
		return x;
	}
	
	public WebElement Sushmeta(int i) {
		WebElement y = a.findElement(By.xpath("(//*[text()='View Result'])[" + i + "]"));
		return y;
	}
	
	public void setBusiness(WebElement business) {
		this.business = business;
	}

	public void setFinanceLink(WebElement financeLink) {
		this.financeLink = financeLink;
	}

	public void setHPB(WebElement hPB) {
		HPB = hPB;
	}

	public void setFHPB(WebElement fHPB) {
		FHPB = fHPB;
	}

	public void setFTPB(WebElement fTPB) {
		FTPB = fTPB;
	}

	public void setFTDPB(WebElement fTDPB) {
		FTDPB = fTDPB;
	}

	public WebElement getFinanceLink() {
		return financeLink;
	}

	public WebElement getBusiness() {
		return business;
	}
	
	public WebElement getPrizeBondList() {
		return PrizeBondList;
	}
	
	public WebElement getPopup() {
		return popup;
	}

	public WebElement getHPB() {
		return HPB;
	}

	public WebElement getTHPB() {
		return THPB;
	}

	public WebElement getSFPBp() {
		return SFPB;
	}

	public WebElement getFHPB() {
		return FHPB;
	}

	public WebElement getSFHPB() {
		return SFHPB;
	}

	public WebElement getFTPB() {
		return FTPB;
	}

	public WebElement getTFTPBp() {
		return TFTPB;
	}
	
	public WebElement getFTDPB() {
		return FTDPB;
	}
	
	public List<WebElement> getTabrow() {
		return tabrow;
	}
	
	public WebElement getRowChange() {
		return rowChange;
	}	
	public WebElement getwinMessage() {
		return winMessage;
	}
	
	public WebElement getpValue() {
		return pValue;
	}
}
