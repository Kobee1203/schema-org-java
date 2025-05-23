/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

/**
 * The act of an agent communicating (service provider, social media, etc) their departure of a previously reserved service (e.g. flight check-in) or place (e.g. hotel).<br/><br/>Related actions:<br/><br/>* [[CheckInAction]]: The antonym of CheckOutAction.<br/>* [[DepartAction]]: Unlike DepartAction, CheckOutAction implies that the agent is informing/confirming the end of a previously reserved service.<br/>* [[CancelAction]]: Unlike CancelAction, CheckOutAction implies that the agent is informing/confirming the end of a previously reserved service.
 *
 * @see <a href="https://schema.org/CheckOutAction">https://schema.org/CheckOutAction</a>
 */
public interface CheckOutAction extends CommunicateAction {
}
