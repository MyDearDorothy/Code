package example
import groovy.json.JsonSlurper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

public class WebUICustomKeywords {

	/**
	 *   获取HTML表格的总行数
	 * @param table：HTML表格
	 * @param outerTagName：HTML表格下面的tag标签，一般为tbody
	 * @return 返回HTML表格行的所有行数
	 */
	@Keyword
	def List getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 *   获取HTML表格行的所有单元格
	 * @param row：HTML表格的总行数
	 * @param tagName：HTML表格下面的tag标签，一般为：TD或TH
	 * @return All cells inside HTML table row 返回HTML表格行的所有单元格
	 */
	@Keyword
	def List getHtmlTableColumns(WebElement row, String tagName) {
		List selectedColumns = row.findElements(By.tagName(tagName))
		return selectedColumns
	}
}