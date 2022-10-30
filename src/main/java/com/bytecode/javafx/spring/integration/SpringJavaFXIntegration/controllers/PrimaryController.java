package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class PrimaryController implements Initializable  {

    private final static Logger LOGGER = LogManager.getLogger(PrimaryController.class.getName());
    //public String Segunda_Pantalla = "modelo_403_v1";
    public String Segunda_Pantalla = "/views/PassPortLogin";


    @FXML
    public void initialize() {
        // Funcion de inicio del Controlador
        //
    }

    @FXML
    private void switchToSecondary() throws IOException {
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
    }

    @FXML
    private void switchToSP() throws IOException {
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToEN() throws IOException {
        Locale locale = new Locale("en", "US");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToPT() throws IOException {
        Locale locale = new Locale("pt", "PT");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToAR() throws IOException {
        Locale locale = new Locale("ar", "AR");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToZH() throws IOException {
        Locale locale = new Locale("zh", "ZH");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToFR() throws IOException {
        Locale locale = new Locale("fr", "FR");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToIT() throws IOException {
        Locale locale = new Locale("it", "IT");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToJA() throws IOException {
        Locale locale = new Locale("ja", "JA");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToKO() throws IOException {
        Locale locale = new Locale("ko", "KO");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToRU() throws IOException {
        Locale locale = new Locale("ru", "RU");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToDE() throws IOException {
        Locale locale = new Locale("de", "DE");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

 
    @FXML
    private void switchToTH() throws IOException {
        Locale locale = new Locale("th", "TH");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}


    @FXML
    private void switchToLanguage(String vlanguage, String vcountry) throws IOException {
        Locale locale = new Locale(vlanguage, vcountry);
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}


    @FXML
    private void switchToExit() throws IOException {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method, no se está usando el método por ahora
        //
        LOGGER.log(Level.INFO, "User successfully logged in {}");
    }


    /*------------------------------
    *  LISTA DE LENGUAJE I18N
    *

        Albanian (sq)	        Albania (AL)		        (Latn)	sq-AL
        Arabic (ar)	            Algeria (DZ)		        (Arab)	ar-DZ
        Arabic (ar)	            Bahrain (BH)		        (Arab)	ar-BH
        Arabic (ar)	            Egypt (EG)		            (Arab)	ar-EG
        Arabic (ar)	            Iraq (IQ)		            (Arab)	ar-IQ
        Arabic (ar)	            Jordan (JO)		            (Arab)	ar-JO
        Arabic (ar)	            Kuwait (KW)		            (Arab)	ar-KW
        Arabic (ar)	            Lebanon (LB)		        (Arab)	ar-LB
        Arabic (ar)	            Libya (LY)		            (Arab)	ar-LY
        Arabic (ar)	            Morocco (MA)		        (Arab)	ar-MA
        Arabic (ar)	            Oman (OM)		            (Arab)	ar-OM
        Arabic (ar)	            Qatar (QA)		            (Arab)	ar-QA
        Arabic (ar)	            Saudi Arabia (SA)	        (Arab)	ar-SA
        Arabic (ar)	            Sudan (SD)		            (Arab)	ar-SD
        Arabic (ar)	            Syria (SY)		            (Arab)	ar-SY
        Arabic (ar)	            Tunisia (TN)		        (Arab)	ar-TN
        Arabic (ar)	            United Arab Emirates (AE)	(Arab)	ar-AE
        Arabic (ar)	            Yemen (YE)		            (Arab)	ar-YE
        Belarusian (be)	        Belarus (BY)		        (Cyrl)	be-BY
        Bulgarian (bg)	        Bulgaria (BG)		        (Cyrl)	bg-BG
        Catalan (ca)	        Spain (ES)		            (Latn)	ca-ES
        Chinese (zh)	        China (CN)		            (Hans)	zh-CN
        Chinese (zh)	        Singapore (SG)		        (Hans)	zh-SG(*)
        Chinese (zh)	        Hong Kong (HK)		        (Hant)	zh-HK
        Chinese (zh)	        Taiwan (TW)		            (Hant)	zh-TW
        Croatian (hr)	        Croatia (HR)		        (Latn)	hr-HR
        Czech (cs)	            Czech Republic (CZ)		    (Latn)	cs-CZ
        Danish (da)	            Denmark (DK)		        (Latn)	da-DK
        Dutch (nl)	            Belgium (BE)		        (Latn)	nl-BE
        Dutch (nl)	            Netherlands (NL)		    (Latn)	nl-NL
        English (en)	        Australia (AU)		        (Latn)	en-AU
        English (en)	        Canada (CA)		            (Latn)	en-CA
        English (en)	        India (IN)		            (Latn)	en-IN
        English (en)	        Ireland (IE)		        (Latn)	en-IE
        English (en)	        Malta (MT)		            (Latn)	en-MT(*)
        English (en)	        New Zealand (NZ)		    (Latn)	en-NZ
        English (en)	        Philippines (PH)		    (Latn)	en-PH(*)
        English (en)	        Singapore (SG)		        (Latn)	en-SG(*)
        English (en)	        South Africa (ZA)		    (Latn)	en-ZA
        English (en)	        United Kingdom (GB)		    (Latn)	en-GB
        English (en)	        United States (US)		    (Latn)	en-US
        Estonian (et)	        Estonia (EE)		        (Latn)	et-EE
        Finnish (fi)	        Finland (FI)		        (Latn)	fi-FI
        French (fr)	            Belgium (BE)		        (Latn)	fr-BE
        French (fr)	            Canada (CA)		            (Latn)	fr-CA
        French (fr)	            France (FR)		            (Latn)	fr-FR
        French (fr)	            Luxembourg (LU)		        (Latn)	fr-LU
        French (fr)	            Switzerland (CH)		    (Latn)	fr-CH
        German (de)	            Austria (AT)		        (Latn)	de-AT
        German (de)	            Germany (DE)		        (Latn)	de-DE
        German (de)	            Luxembourg (LU)		        (Latn)	de-LU
        German (de)	            Switzerland (CH)		    (Latn)	de-CH
        Greek (el)	            Cyprus (CY)		            (Grek)	el-CY(*)
        Greek (el)	            Greece (GR)		            (Grek)	el-GR
        Hebrew (iw)	            Israel (IL)		            (Hebr)	iw-IL
        Hindi (hi)	            India (IN)		            (Deva)	hi-IN
        Hungarian (hu)	        Hungary (HU)		        (Latn)	hu-HU
        Icelandic (is)	        Iceland (IS)		        (Latn)	is-IS
        Indonesian (in)	        Indonesia (ID)		        (Latn)	in-ID(*)
        Irish (ga)	            Ireland (IE)		        (Latn)	ga-IE(*)
        Italian (it)	        Italy (IT)		            (Latn)	it-IT
        Italian (it)	        Switzerland (CH)		    (Latn)	it-CH
        Japanese (ja)	        Japan (JP)		            (Jpan)	ja-JP
        Japanese (ja)	        Japan (JP)	*	            (Jpan)	ja-JP-u-ca-japanese
        Japanese (ja)	        Japan (JP)	JP          	(Jpan)	ja-JP-x-lvariant-JP
        Korean (ko)	            South Korea (KR)		    (Kore)	ko-KR
        Latvian (lv)	        Latvia (LV)		            (Latn)	lv-LV
        Lithuanian (lt)	        Lithuania (LT)		        (Latn)	lt-LT
        Macedonian (mk)	        Macedonia (MK)		        (Cyrl)	mk-MK
        Malay (ms)	            Malaysia (MY)		        (Latn)	ms-MY(*)
        Maltese (mt)	        Malta (MT)		            (Latn)	mt-MT(*)
        Norwegian (no)	        Norway (NO)		            (Latn)	no-NO
        Norwegian Bokmål (nb)	Norway (NO)		            (Latn)	nb-NO
        Norwegian Nynorsk (nn)	Norway (NO)		            (Latn)	nn-NO
        Norwegian (no)	        Norway (NO)	NY	            (Latn)	no-NO-x-lvariant-NY
        Polish (pl)	            Poland (PL)		            (Latn)	pl-PL
        Portuguese (pt)	        Brazil (BR)		            (Latn)	pt-BR(***)
        Portuguese (pt)	        Portugal (PT)		        (Latn)	pt-PT(***)
        Romanian (ro)	        Romania (RO)		        (Latn)	ro-RO
        Russian (ru)	        Russia (RU)		            (Cyrl)	ru-RU
        Serbian (sr)	        Bosnia and Herzegovina (BA)	(Cyrl)	sr-BA(*)
        Serbian (sr)	        Montenegro (ME)		        (Cyrl)	sr-ME(*)
        Serbian (sr)	        Serbia (RS)		            (Cyrl)	sr-RS(*)
        Serbian (sr)	        Bosnia and Herzegovina (BA)	(Latn)	sr-Latn-BA(**)
        Serbian (sr)	        Montenegro (ME)		        (Latn)	sr-Latn-ME(**)
        Serbian (sr)	        Serbia (RS)		            (Latn)	sr-Latn-RS(**)
        Slovak (sk)	            Slovakia (SK)		        (Latn)	sk-SK
        Slovenian (sl)	        Slovenia (SI)		        (Latn)	sl-SI
        Spanish (es)	        Argentina (AR)		        (Latn)	es-AR
        Spanish (es)	        Bolivia (BO)		        (Latn)	es-BO
        Spanish (es)	        Chile (CL)		            (Latn)	es-CL
        Spanish (es)	        Colombia (CO)		        (Latn)	es-CO
        Spanish (es)	        Costa Rica (CR)		        (Latn)	es-CR
        Spanish (es)	        Dominican Republic (DO)		(Latn)	es-DO
        Spanish (es)	        Ecuador (EC)		        (Latn)	es-EC
        Spanish (es)	        El Salvador (SV)		    (Latn)	es-SV
        Spanish (es)	        Guatemala (GT)		        (Latn)	es-GT
        Spanish (es)	        Honduras (HN)		        (Latn)	es-HN
        Spanish (es)	        Mexico (MX)		            (Latn)	es-MX
        Spanish (es)	        Nicaragua (NL)		        (Latn)	es-NI
        Spanish (es)	        Panama (PA)		            (Latn)	es-PA
        Spanish (es)	        Paraguay (PY)		        (Latn)	es-PY
        Spanish (es)	        Peru (PE)		            (Latn)	es-PE
        Spanish (es)	        Puerto Rico (PR)		    (Latn)	es-PR
        Spanish (es)	        Spain (ES)		            (Latn)	es-ES
        Spanish (es)	        United States (US)		    (Latn)	es-US(*)
        Spanish (es)	        Uruguay (UY)		        (Latn)	es-UY
        Spanish (es)	        Venezuela (VE)		        (Latn)	es-VE
        Swedish (sv)	        Sweden (SE)		            (Latn)	sv-SE
        Thai (th)	            Thailand (TH)		        (Thai)	th-TH
        Thai (th)	            Thailand (TH)	*	        (Thai)	th-TH-u-ca-buddhist
        Thai (th)	            Thailand (TH)	*	        (Thai)	th-TH-u-ca-buddhist-nu-thai
        Thai (th)	            Thailand (TH)	TH	        (Thai)	th-TH-x-lvariant-TH
        Turkish (tr)	        Turkey (TR)		            (Latn)	tr-TR
        Ukrainian (uk)	        Ukraine (UA)		        (Cyrl)	uk-UA
        Vietnamese (vi)	        Vietnam (VN)		        (Latn)	vi-VN

    */

}
