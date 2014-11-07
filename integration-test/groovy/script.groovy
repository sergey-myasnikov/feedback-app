import groovy.json.JsonSlurper
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext
import com.eviware.soapui.support.GroovyUtils

// Run a single test step by test step name
def runTestStep(testCaseInstance, testStepName){

  def testRunner = new WsdlTestCaseRunner(testCaseInstance, null);
  def testStepInstance = testCaseInstance.getTestStepByName(testStepName);
  def testStepContext = new WsdlTestRunContext(testStepInstance);
  
  testStepInstance.run(testRunner, testStepContext);
}

// Run a single test step by test step name after setting a property
def runTestStep(testCaseInstance, testStepName, propertyName, propertyValue){

  def testRunner = new WsdlTestCaseRunner(testCaseInstance, null);
  def testStepInstance = testCaseInstance.getTestStepByName(testStepName);
  def testStepContext = new WsdlTestRunContext(testStepInstance);
  
  testStepInstance.setPropertyValue(propertyName, propertyValue);
  testStepInstance.run(testRunner, testStepContext);
}

//Set test step property value by name
def setTestStepProperty(testCaseInstance, testStepName, propertyName, propertyValue){
  
  def testRunner = new WsdlTestCaseRunner(testCaseInstance, null);
  def testStepInstance = testCaseInstance.getTestStepByName(testStepName);

  testStepInstance.setPropertyValue(propertyName, propertyValue);
}

// Get step response by test step name
def getResponseByStep(testCaseInstance, testStepName){

  def testStepInstance = testCaseInstance.getTestStepByName(testStepName);
  def testStepContext = new WsdlTestRunContext(testStepInstance);

  def response = testStepContext.expand('${' + testStepName +'#Response}')
  return response
}

// Get step response JSON by test step name
def getJsonResponseByStep(testCaseInstance, testStepName){

  def response = getResponseByStep(testCaseInstance, testStepName)
  def slurper = new JsonSlurper()
  def json = slurper.parseText response
  
  return json
}

// Verify that JSON result is not empty for a given test step
def verifyJsonNotEmptyByStep(testCaseInstance, testStepName){
  verifyJsonIsGreaterThenGiven(testCaseInstance, testStepName, 0)
}

// Verify that JSON result size is greater then given for a given test step
def verifyJsonIsGreaterThenGiven(testCaseInstance, testStepName, minSize){

  def json = getJsonResponseByStep(testCaseInstance, testStepName)
  
  assert json != null, 'Result JSON is NULL for ' + testStepName + ' step'
  assert json.size > minSize, 'Result JSON size is less or = ' + minSize + ' for ' + testStepName + ' step'
}

// Create new file in 'results-output' directory
def createNewFile(groovyUtils, fileName){

  File file = null
  
  if (System.properties['os.name'].toLowerCase().contains('windows')) {
    def projectDir = groovyUtils.projectPath
    file = new File(projectDir + "\\results-output\\" + fileName)
  } else {
    file = new File( "./results-output/" + fileName)
  }
  
  return file
}

// Delete file in 'results-output' directory
def deleteFile(groovyUtils, fileName) {

  def file = createNewFile(groovyUtils, fileName)
  file.delete()

}

// Get current line separator
def getLineSeparator(){
  return System.getProperty('line.separator')
}

// Method to verify VM flavor
def verifyProjectFlavorByJson(projectID, json, flavor, fileName, groovyUtils){
  
  def file = createNewFile(groovyUtils, fileName)
	
  json.each {
		if(  (it.flavor.toUpperCase() != (flavor).toUpperCase()) ){
		file << projectID + "\t" + it.identifier + "\t" + it.flavor.toUpperCase() + getLineSeparator()
		}
	}
}













