package springboot.samples.jwt01.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country implements Serializable {

  @Id
  @Column(name = "country_name")
  private String countryName;

  @Column(name = "phone_prefix")
  private String phonePrefix;

  @Column(name = "iso_code1")
  private String isoCode1;

  @Column(name = "iso_code2")
  private String isoCode2;

  public Country() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Country(String countryName, String phonePrefix, String isoCode1, String isoCode2) {
    super();
    this.countryName = countryName;
    this.phonePrefix = phonePrefix;
    this.isoCode1 = isoCode1;
    this.isoCode2 = isoCode2;
  }


  public String getCountryName() {
    return countryName;
  }


  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }


  public String getPhonePrefix() {
    return phonePrefix;
  }


  public void setPhonePrefix(String phonePrefix) {
    this.phonePrefix = phonePrefix;
  }


  public String getIsoCode1() {
    return isoCode1;
  }


  public void setIsoCode1(String isoCode1) {
    this.isoCode1 = isoCode1;
  }


  public String getIsoCode2() {
    return isoCode2;
  }


  public void setIsoCode2(String isoCode2) {
    this.isoCode2 = isoCode2;
  }


}
