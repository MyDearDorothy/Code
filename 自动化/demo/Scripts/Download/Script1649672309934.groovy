import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

for (def index : (0..1390)) {
    WebUI.navigateToUrl('http://www.cninfo.com.cn/new/index')

    WebUI.click(findTestObject('Download/input__el-input__inner'))

    WebUI.setText(findTestObject('Download/input__el-input__inner'), findTestData('样本').getValue(1, 1 + index))

    WebUI.setText(findTestObject('Download/input_keyword'), '年报')

    WebUI.setText(findTestObject('Download/input__el-range-input'), '2009-01-01')

    WebUI.setText(findTestObject('Download/input__el-range-input_1'), '2020-01-01')

    WebUI.sendKeys(findTestObject('Download/input__el-range-input'), Keys.chord(Keys.ENTER))

    WebUI.sendKeys(findTestObject('Download/input__el-range-input_1'), Keys.chord(Keys.ENTER))

    WebUI.click(findTestObject('Download/btn_search'))

    WebUI.delay(2)

    table = findTestObject('add/table')

    //	println(table)
    TableRows = CustomKeywords.'example.WebUICustomKeywords.getHtmlTableRows'(table, 'tbody')

    //	println(TableRows.size())   打印总行数
    for (def row : (0..TableRows.size())) {
        WebUI.click(findTestObject('add/row', [('number') : row + 1]))

        WebUI.switchToWindowIndex(1)

        //      TableRow = CustomKeywords.'example.WebUICustomKeywords.getHtmlTableColumns'(TableRows[row], 'td')
        //      Account = TableRow[2].text
        //		println(Account)
        WebUI.delay(3)

        WebUI.click(findTestObject('add/btn_downlaod'))

        WebUI.closeWindowIndex(1)

        WebUI.switchToWindowIndex(0)
    }
    
    while (WebUI.verifyElementClickable(findTestObject('Download/next'))) {
        WebUI.click(findTestObject('Download/next'))

        WebUI.delay(2)

        table = findTestObject('add/table')

        TableRows = CustomKeywords.'example.WebUICustomKeywords.getHtmlTableRows'(table, 'tbody')

        for (def row : (0..TableRows.size())) {
			
			WebUI.click(findTestObject('add/row', [('number') : row + 1]))
			
			WebUI.switchToWindowIndex(1)
	
			WebUI.delay(3)
	
			WebUI.click(findTestObject('add/btn_downlaod'))
	
			WebUI.closeWindowIndex(1)
	
			WebUI.switchToWindowIndex(0)
        }
    }
}

