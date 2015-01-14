
package org.trialversionv1.main;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.trialversionv1.main package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EncryptExpDateResponse_QNAME = new QName("http://main.trialVersionv1.org/", "encryptExpDateResponse");
    private final static QName _SubscriptionRateResponse_QNAME = new QName("http://main.trialVersionv1.org/", "subscriptionRateResponse");
    private final static QName _SubscriptionRate_QNAME = new QName("http://main.trialVersionv1.org/", "subscriptionRate");
    private final static QName _EncryptExpDate_QNAME = new QName("http://main.trialVersionv1.org/", "encryptExpDate");
    private final static QName _GenerateKey_QNAME = new QName("http://main.trialVersionv1.org/", "generateKey");
    private final static QName _GenerateKeyResponse_QNAME = new QName("http://main.trialVersionv1.org/", "generateKeyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.trialversionv1.main
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerateKey }
     * 
     */
    public GenerateKey createGenerateKey() {
        return new GenerateKey();
    }

    /**
     * Create an instance of {@link EncryptExpDate }
     * 
     */
    public EncryptExpDate createEncryptExpDate() {
        return new EncryptExpDate();
    }

    /**
     * Create an instance of {@link GenerateKeyResponse }
     * 
     */
    public GenerateKeyResponse createGenerateKeyResponse() {
        return new GenerateKeyResponse();
    }

    /**
     * Create an instance of {@link SubscriptionRateResponse }
     * 
     */
    public SubscriptionRateResponse createSubscriptionRateResponse() {
        return new SubscriptionRateResponse();
    }

    /**
     * Create an instance of {@link EncryptExpDateResponse }
     * 
     */
    public EncryptExpDateResponse createEncryptExpDateResponse() {
        return new EncryptExpDateResponse();
    }

    /**
     * Create an instance of {@link SubscriptionRate }
     * 
     */
    public SubscriptionRate createSubscriptionRate() {
        return new SubscriptionRate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptExpDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "encryptExpDateResponse")
    public JAXBElement<EncryptExpDateResponse> createEncryptExpDateResponse(EncryptExpDateResponse value) {
        return new JAXBElement<EncryptExpDateResponse>(_EncryptExpDateResponse_QNAME, EncryptExpDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionRateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "subscriptionRateResponse")
    public JAXBElement<SubscriptionRateResponse> createSubscriptionRateResponse(SubscriptionRateResponse value) {
        return new JAXBElement<SubscriptionRateResponse>(_SubscriptionRateResponse_QNAME, SubscriptionRateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionRate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "subscriptionRate")
    public JAXBElement<SubscriptionRate> createSubscriptionRate(SubscriptionRate value) {
        return new JAXBElement<SubscriptionRate>(_SubscriptionRate_QNAME, SubscriptionRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptExpDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "encryptExpDate")
    public JAXBElement<EncryptExpDate> createEncryptExpDate(EncryptExpDate value) {
        return new JAXBElement<EncryptExpDate>(_EncryptExpDate_QNAME, EncryptExpDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "generateKey")
    public JAXBElement<GenerateKey> createGenerateKey(GenerateKey value) {
        return new JAXBElement<GenerateKey>(_GenerateKey_QNAME, GenerateKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.trialVersionv1.org/", name = "generateKeyResponse")
    public JAXBElement<GenerateKeyResponse> createGenerateKeyResponse(GenerateKeyResponse value) {
        return new JAXBElement<GenerateKeyResponse>(_GenerateKeyResponse_QNAME, GenerateKeyResponse.class, null, value);
    }

}
