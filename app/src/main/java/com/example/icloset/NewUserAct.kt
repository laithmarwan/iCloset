package com.example.icloset
import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_new_user.*
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*

class NewUserAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)


       /* var x = "Amman|Aqaba|Irbid|Zarqa|Karak|Salt|Cairo|Beirut|Badakhshan|Badghis|Baghlan|Balkh|Bamian|Farah|Faryab|Ghazni|Ghowr|Helmand|Herat|Jowzjan|Kabol|" +
                "Kandahar|Kapisa|Konar|Kondoz|Laghman|Lowgar|Nangarhar|Nimruz|Oruzgan|Paktia|Paktika|Parvan|Samangan|Sar-e Pol|" +
                "Takhar|Vardak|Zabol|Berat|Bulqize|Delvine|Devoll (Bilisht)|Diber (Peshkopi)|Durres|Elbasan|Fier|Gjirokaster|Gramsh|" +
                "Has (Krume)|Kavaje|Kolonje (Erseke)|Korce|Kruje|Kucove|Kukes|Kurbin|Lezhe|Librazhd|Lushnje|Malesi e Madhe (Koplik)|" +
                "Mallakaster (Ballsh)|Mat (Burrel)|Mirdite (Rreshen)|Peqin|Permet|Pogradec|Puke|Sarande|Shkoder|Skrapar (Corovode)|" +
                "Tepelene|Tirane (Tirana)|Tirane (Tirana)|Tropoje (Bajram Curri)|Vlore|Adrar|Ain Defla|Ain Temouchent|Alger|Annaba|Batna|" +
                "Bechar|Bejaia|Biskra|Blida|Bordj Bou Arreridj|Bouira|Boumerdes|Chlef|Constantine|Djelfa|El Bayadh|El Oued|El Tarf|Ghardaia|" +
                "Guelma|Illizi|Jijel|Khenchela|Laghouat|M'Sila|Mascara|Medea|Mila|Mostaganem|Naama|Oran|Ouargla|Oum el Bouaghi|Relizane|Saida|" +
                "Setif|Sidi Bel Abbes|Skikda|Souk Ahras|Tamanghasset|Tebessa|Tiaret|Tindouf|Tipaza|Tissemsilt|Tizi Ouzou|Tlemcen|Eastern|Manu'a|" +
                "Rose Island|Swains Island|Western|Andorra la Vella|Bengo|Benguela|Bie|Cabinda|Canillo|Cuando Cubango|Cuanza Norte|Cuanza Sul|Cunene|" +
                "Encamp|Escaldes-Engordany|Huambo|Huila|La Massana|Luanda|Lunda Norte|Lunda Sul|Malanje|Moxico|Namibe|Ordino|Sant Julia de Loria|Uige|" +
                "Zaire|Anguilla|Antartica|Barbuda|Redonda|Saint George|Saint John|Saint Mary|Saint Paul|Saint Peter|Saint Philip|Antartica e Islas del Atlantico Sur|" +
                "Buenos Aires|Buenos Aires Capital Federal|Catamarca|Chaco|Chubut|Cordoba|Corrientes|Entre Rios|Formosa|Jujuy|La Pampa|La Rioja|Mendoza|" +
                "Misiones|Neuquen|Rio Negro|Salta|San Juan|San Luis|Santa Cruz|Santa Fe|Santiago del Estero|Tierra del Fuego|Tucuman|Aragatsotn|Ararat|" +
                "Armavir|Geghark'unik'|Kotayk'|Lorri|Shirak|Syunik'|Tavush|Vayots' Dzor|Yerevan|Aruba|Ashmore and Cartier Island|Australian Capital Territory|" +
                "New South Wales|Northern Territory|Queensland|South Australia|Tasmania|Victoria|Western Australia|Burgenland|Kaernten|Niederoesterreich|" +
                "Oberoesterreich|Salzburg|Steiermark|Tirol|Vorarlberg|Wien|Abseron Rayonu|Agcabadi Rayonu|Agdam Rayonu|Agdas Rayonu|Agstafa Rayonu|Agsu Rayonu|" +
                "Ali Bayramli Sahari|Astara Rayonu|Baki Sahari|Balakan Rayonu|Barda Rayonu|Beylaqan Rayonu|Bilasuvar Rayonu|Cabrayil Rayonu|Calilabad Rayonu|" +
                "Daskasan Rayonu|Davaci Rayonu|Fuzuli Rayonu|Gadabay Rayonu|Ganca Sahari|Goranboy Rayonu|Goycay Rayonu|Haciqabul Rayonu|Imisli Rayonu|" +
                "Ismayilli Rayonu|Kalbacar Rayonu|Kurdamir Rayonu|Lacin Rayonu|Lankaran Rayonu|Lankaran Sahari|Lerik Rayonu|Masalli Rayonu|Mingacevir Sahari|" +
                "Naftalan Sahari|Naxcivan Muxtar Respublikasi|Neftcala Rayonu|Oguz Rayonu|Qabala Rayonu|Qax Rayonu|Qazax Rayonu|Qobustan Rayonu|Quba Rayonu|" +
                "Qubadli Rayonu|Qusar Rayonu|Saatli Rayonu|Sabirabad Rayonu|Saki Rayonu|Saki Sahari|Salyan Rayonu|Samaxi Rayonu|Samkir Rayonu|Samux Rayonu|" +
                "Siyazan Rayonu|Sumqayit Sahari|Susa Rayonu|Susa Sahari|Tartar Rayonu|Tovuz Rayonu|Ucar Rayonu|Xacmaz Rayonu|Xankandi Sahari|Xanlar Rayonu|" +
                "Xizi Rayonu|Xocali Rayonu|Xocavand Rayonu|Yardimli Rayonu|Yevlax Rayonu|Yevlax Sahari|Zangilan Rayonu|Zaqatala Rayonu|Zardab Rayonu|" +
                "Acklins and Crooked Islands|Bimini|Cat Island|Exuma|Freeport|Fresh Creek|Governor's Harbour|Green Turtle Cay|Harbour Island|High Rock|" +
                "Inagua|Kemps Bay|Long Island|Marsh Harbour|Mayaguana|New Providence|Nicholls Town and Berry Islands|Ragged Island|Rock Sound|" +
                "San Salvador and Rum Cay|Sandy Point|Al Hadd|Al Manamah|Al Mintaqah al Gharbiyah|Al Mintaqah al Wusta|Al Mintaqah ash Shamaliyah|" +
                "Al Muharraq|Ar Rifa' wa al Mintaqah al Janubiyah|Jidd Hafs|Juzur Hawar|Madinat 'Isa|Madinat Hamad|Sitrah|Barguna|Barisal|Bhola|" +
                "Jhalokati|Patuakhali|Pirojpur|Bandarban|Brahmanbaria|Chandpur|Chittagong|Comilla|Cox's Bazar|Feni|Khagrachari|Lakshmipur|Noakhali|" +
                "Rangamati|Dhaka|Faridpur|Gazipur|Gopalganj|Jamalpur|Kishoreganj|Madaripur|Manikganj|Munshiganj|Mymensingh|Narayanganj|Narsingdi|" +
                "Netrokona|Rajbari|Shariatpur|Sherpur|Tangail|Bagerhat|Chuadanga|Jessore|Jhenaidah|Khulna|Kushtia|Magura|Meherpur|Narail|Satkhira|" +
                "Bogra|Dinajpur|Gaibandha|Jaipurhat|Kurigram|Lalmonirhat|Naogaon|Natore|Nawabganj|Nilphamari|Pabna|Panchagarh|Rajshahi|Rangpur|" +
                "Sirajganj|Thakurgaon|Habiganj|Maulvi bazar|Sunamganj|Sylhet|Bridgetown|Christ Church|Saint Andrew|Saint George|Saint James|" +
                "Saint John|Saint Joseph|Saint Lucy|Saint Michael|Saint Peter|Saint Philip|Saint Thomas|Brestskaya (Brest)|Homyel'skaya (Homyel')|" +
                "Horad Minsk|Hrodzyenskaya (Hrodna)|Mahilyowskaya (Mahilyow)|Minskaya|Vitsyebskaya (Vitsyebsk)|Antwerpen|Brabant Wallon|" +
                "Brussels Capitol Region|Hainaut|Liege|Limburg|Luxembourg|Namur|Oost-Vlaanderen|Vlaams Brabant|West-Vlaanderen|Belize|Cayo|Corozal|" +
                "Orange Walk|Stann Creek|Toledo|Alibori|Atakora|Atlantique|Borgou|Collines|Couffo|Donga|Littoral|Mono|Oueme|Plateau|Zou|Devonshire|" +
                "Hamilton|Hamilton|Paget|Pembroke|Saint George|Saint Georges|Sandys|Smiths|Southampton|Warwick|Bumthang|Chhukha|Chirang|Daga|Geylegphug|" +
                "Ha|Lhuntshi|Mongar|Paro|Pemagatsel|Punakha|Samchi|Samdrup Jongkhar|Shemgang|Tashigang|Thimphu|Tongsa|Wangdi Phodrang|Beni|Chuquisaca|" +
                "Cochabamba|La Paz|Oruro|Pando|Potosi|Santa Cruz|Tarija|Federation of Bosnia and Herzegovina|Republika Srpska|Central|Chobe|Francistown|" +
                "Gaborone|Ghanzi|Kgalagadi|Kgatleng|Kweneng|Lobatse|Ngamiland|North-East|Selebi-Pikwe|South-East|Southern|Acre|Alagoas|Amapa|Amazonas|" +
                "Bahia|Ceara|Distrito Federal|Espirito Santo|Goias|Maranhao|Mato Grosso|Mato Grosso do Sul|Minas Gerais|Para|Paraiba|Parana|Pernambuco|" +
                "Piaui|Rio de Janeiro|Rio Grande do Norte|Rio Grande do Sul|Rondonia|Roraima|Santa Catarina|Sao Paulo|Sergipe|Tocantins|Anegada|Jost Van Dyke|" +
                "Tortola|Virgin Gorda|Belait|Brunei and Muara|Temburong|Tutong|Blagoevgrad|Burgas|Dobrich|Gabrovo|Khaskovo|Kurdzhali|Kyustendil|Lovech|Montana|" +
                "Pazardzhik|Pernik|Pleven|Plovdiv|Razgrad|Ruse|Shumen|Silistra|Sliven|Smolyan|Sofiya|Sofiya-Grad|Stara Zagora|Turgovishte|Varna|Veliko Turnovo|" +
                "Vidin|Vratsa|Yambol|Bale|Bam|Banwa|Bazega|Bougouriba|Boulgou|Boulkiemde|Comoe|Ganzourgou|Gnagna|Gourma|Houet|Ioba|Kadiogo|Kenedougou|Komandjari|" +
                "Kompienga|Kossi|Koupelogo|Kouritenga|Kourweogo|Leraba|Loroum|Mouhoun|Nahouri|Namentenga|Naumbiel|Nayala|Oubritenga|Oudalan|Passore|Poni|Samentenga|" +
                "Sanguie|Seno|Sissili|Soum|Sourou|Tapoa|Tuy|Yagha|Yatenga|Ziro|Zondomo|Zoundweogo|Ayeyarwady|Bago|Chin State|Kachin State|Kayah State|Kayin State|" +
                "Magway|Mandalay|Mon State|Rakhine State|Sagaing|Shan State|Tanintharyi|Yangon|Bubanza|Bujumbura|Bururi|Cankuzo|Cibitoke|Gitega|Karuzi|Kayanza|" +
                "Kirundo|Makamba|Muramvya|Muyinga|Mwaro|Ngozi|Rutana|Ruyigi|Banteay Mean Cheay|Batdambang|Kampong Cham|Kampong Chhnang|Kampong Spoe|Kampong Thum|" +
                "Kampot|Kandal|Kaoh Kong|Keb|Kracheh|Mondol Kiri|Otdar Mean Cheay|Pailin|Phnum Penh|Pouthisat|Preah Seihanu (Sihanoukville)|Preah Vihear|Prey Veng|" +
                "Rotanah Kiri|Siem Reab|Stoeng Treng|Svay Rieng|Takev|Adamaoua|Centre|Est|Extreme-Nord|Littoral|Nord|Nord-Ouest|Ouest|Sud|Sud-Ouest|Alberta|" +
                "British Columbia|Manitoba|New Brunswick|Newfoundland|Northwest Territories|Nova Scotia|Nunavut|Ontario|Prince Edward Island|Quebec|Saskatchewan|" +
                "Yukon Territory|Boa Vista|Brava|Maio|Mosteiros|Paul|Porto Novo|Praia|Ribeira Grande|Sal|Santa Catarina|Santa Cruz|Sao Domingos|Sao Filipe|" +
                "Sao Nicolau|Sao Vicente|Tarrafal|Creek|Eastern|Midland|South Town|Spot Bay|Stake Bay|West End|Western|Bamingui-Bangoran|Bangui|Basse-Kotto|" +
                "Gribingui|Haut-Mbomou|Haute-Kotto|Haute-Sangha|Kemo-Gribingui|Lobaye|Mbomou|Nana-Mambere|Ombella-Mpoko|Ouaka|Ouham|Ouham-Pende|Sangha|Vakaga|" +
                "Batha|Biltine|Borkou-Ennedi-Tibesti|Chari-Baguirmi|Guera|Kanem|Lac|Logone Occidental|Logone Oriental|Mayo-Kebbi|Moyen-Chari|Ouaddai|Salamat|" +
                "Tandjile|Aisen del General Carlos Ibanez del Campo|Antofagasta|Araucania|Atacama|Bio-Bio|Coquimbo|Libertador General Bernardo O'Higgins|" +
                "Los Lagos|Magallanes y de la Antartica Chilena|Maule|Region Metropolitana (Santiago)|Tarapaca|Valparaiso|Anhui|Beijing|Chongqing|Fujian|" +
                "Gansu|Guangdong|Guangxi|Guizhou|Hainan|Hebei|Heilongjiang|Henan|Hubei|Hunan|Jiangsu|Jiangxi|Jilin|Liaoning|Nei Mongol|Ningxia|Qinghai|Shaanxi|" +
                "Shandong|Shanghai|Shanxi|Sichuan|Tianjin|Xinjiang|Xizang (Tibet)|Yunnan|Zhejiang|Christmas Island|Clipperton Island|Direction Island|Home Island|" +
                "Horsburgh Island|North Keeling Island|South Island|West Island|Amazonas|Antioquia|Arauca|Atlantico|Bolivar|Boyaca|Caldas|Caqueta|Casanare|Cauca|" +
                "Cesar|Choco|Cordoba|Cundinamarca|Distrito Capital de Santa Fe de Bogota|Guainia|Guaviare|Huila|La Guajira|Magdalena|Meta|Narino|Norte de Santander|" +
                "Putumayo|Quindio|Risaralda|San Andres y Providencia|Santander|Sucre|Tolima|Valle del Cauca|Vaupes|Vichada|Anjouan (Nzwani)|Domoni|Fomboni|" +
                "Grande Comore (Njazidja)|Moheli (Mwali)|Moroni|Moutsamoudou |Bandundu|Bas-Congo|Equateur|Kasai-Occidental|Kasai-Oriental|Katanga|Kinshasa|" +
                "Maniema|Nord-Kivu|Orientale|Sud-Kivu |Bouenza|Brazzaville|Cuvette|Kouilou|Lekoumou|Likouala|Niari|Plateaux|Pool|Sangha |Aitutaki|Atiu|Avarua|" +
                "Mangaia|Manihiki|Manuae|Mauke|Mitiaro|Nassau Island|Palmerston|Penrhyn|Pukapuka|Rakahanga|Rarotonga|Suwarrow|Takutea |Alajuela|Cartago|Guanacaste|" +
                "Heredia|Limon|Puntarenas|San Jose |Abengourou|Abidjan|Aboisso|Adiake'|Adzope|Agboville|Agnibilekrou|Ale'pe'|Bangolo|Beoumi|Biankouma|Bocanda|" +
                "Bondoukou|Bongouanou|Bouafle|Bouake|Bouna|Boundiali|Dabakala|Dabon|Daloa|Danane|Daoukro|Dimbokro|Divo|Duekoue|Ferkessedougou|Gagnoa|Grand Bassam|" +
                "Grand-Lahou|Guiglo|Issia|Jacqueville|Katiola|Korhogo|Lakota|Man|Mankono|Mbahiakro|Odienne|Oume|Sakassou|San-Pedro|Sassandra|Seguela|Sinfra|Soubre|" +
                "Tabou|Tanda|Tiassale|Tiebissou|Tingrela|Touba|Toulepleu|Toumodi|Vavoua|Yamoussoukro|Zuenoula |Bjelovarsko-Bilogorska Zupanija|Brodsko-Posavska Zupanija|" +
                "Dubrovacko-Neretvanska Zupanija|Istarska Zupanija|Karlovacka Zupanija|Koprivnicko-Krizevacka Zupanija|Krapinsko-Zagorska Zupanija|Licko-Senjska Zupanija|" +
                "Medimurska Zupanija|Osjecko-Baranjska Zupanija|Pozesko-Slavonska Zupanija|Primorsko-Goranska Zupanija|Sibensko-Kninska Zupanija|Sisacko-Moslavacka Zupanija|" +
                "Splitsko-Dalmatinska Zupanija|Varazdinska Zupanija|Viroviticko-Podravska Zupanija|Vukovarsko-Srijemska Zupanija|Zadarska Zupanija|Zagreb|Zagrebacka Zupanija|" +
                "Camaguey|Ciego de Avila|Cienfuegos|Ciudad de La Habana|Granma|Guantanamo|Holguin|Isla de la Juventud|La Habana|Las Tunas|Matanzas|Pinar del Rio|Sancti Spiritus|" +
                "Santiago de Cuba|Villa Clara |Famagusta|Kyrenia|Larnaca|Limassol|Nicosia|Paphos |Brnensky|Budejovicky|Jihlavsky|Karlovarsky|Kralovehradecky|Liberecky|Olomoucky|" +
                "Ostravsky|Pardubicky|Plzensky|Praha|Stredocesky|Ustecky|Zlinsky |Arhus|Bornholm|Fredericksberg|Frederiksborg|Fyn|Kobenhavn|Kobenhavns|Nordjylland|Ribe|Ringkobing|" +
                "Roskilde|Sonderjylland|Storstrom|Vejle|Vestsjalland|Viborg |'Ali Sabih|Dikhil|Djibouti|Obock|Tadjoura |Saint Andrew|Saint David|Saint George|Saint John|Saint Joseph|" +
                "Saint Luke|Saint Mark|Saint Patrick|Saint Paul|Saint Peter |Azua|Baoruco|Barahona|Dajabon|Distrito Nacional|Duarte|El Seibo|Elias Pina|Espaillat|Hato Mayor|" +
                "Independencia|La Altagracia|La Romana|La Vega|Maria Trinidad Sanchez|Monsenor Nouel|Monte Cristi|Monte Plata|Pedernales|Peravia|Puerto Plata|Salcedo|Samana|" +
                "San Cristobal|San Juan|San Pedro de Macoris|Sanchez Ramirez|Santiago|Santiago Rodriguez|Valverde|Azuay|Bolivar|Canar|Carchi|Chimborazo|Cotopaxi|El Oro|" +
                "Esmeraldas|Galapagos|Guayas|Imbabura|Loja|Los Rios|Manabi|Morona-Santiago|Napo|Orellana|Pastaza|Pichincha|Sucumbios|Tungurahua|Zamora-Chinchipe |" +
                "Ad Daqahliyah|Al Bahr al Ahmar|Al Buhayrah|Al Fayyum|Al Gharbiyah|Al Iskandariyah|Al Isma'iliyah|Al Jizah|Al Minufiyah|Al Minya|Al Qahirah|" +
                "Al Qalyubiyah|Al Wadi al Jadid|As Suways|Ash Sharqiyah|Aswan|Asyut|Bani Suwayf|Bur Sa'id|Dumyat|Janub Sina'|Kafr ash Shaykh|Matruh|Qina|" +
                "Shamal Sina'|Suhaj |Ahuachapan|Cabanas|Chalatenango|Cuscatlan|La Libertad|La Paz|La Union|Morazan|San Miguel|San Salvador|San Vicente|" +
                "Santa Ana|Sonsonate|Usulutan |Annobon|Bioko Norte|Bioko Sur|Centro Sur|Kie-Ntem|Litoral|Wele-Nzas |Akale Guzay|Barka|Denkel|Hamasen|Sahil|" +
                "Semhar|Senhit|Seraye |Harjumaa (Tallinn)|Hiiumaa (Kardla)|Ida-Virumaa (Johvi)|Jarvamaa (Paide)|Jogevamaa (Jogeva)|Laane-Virumaa (Rakvere)|" +
                "Laanemaa (Haapsalu)|Parnumaa (Parnu)|Polvamaa (Polva)|Raplamaa (Rapla)|Saaremaa (Kuessaare)|Tartumaa (Tartu)|Valgamaa (Valga)|" +
                "Viljandimaa (Viljandi)|Vorumaa (Voru) |Adis Abeba (Addis Ababa)|Afar|Amara|Dire Dawa|Gambela Hizboch|Hareri Hizb|Oromiya|Sumale|" +
                "Tigray|YeDebub Biheroch Bihereseboch na Hizboch |Europa Island |Falkland Islands (Islas Malvinas) |Bordoy|Eysturoy|Mykines|Sandoy|" +
                "Skuvoy|Streymoy|Suduroy|Tvoroyri|Vagar |Central|Eastern|Northern|Rotuma|Western |Aland|Etela-Suomen Laani|Ita-Suomen Laani|" +
                "Lansi-Suomen Laani|Lappi|Oulun Laani |Alsace|Aquitaine|Auvergne|Basse-Normandie|Bourgogne|Bretagne|Centre|Champagne-Ardenne|Corse|" +
                "Franche-Comte|Haute-Normandie|Ile-de-France|Languedoc-Roussillon|Limousin|Lorraine|Midi-Pyrenees|Nord-Pas-de-Calais|Pays de la Loire|" +
                "Picardie|Poitou-Charentes|Provence-Alpes-Cote d'Azur|Rhone-Alpes |French Guiana |Archipel des Marquises|Archipel des Tuamotu|Archipel des Tubuai|" +
                "Iles du Vent|Iles Sous-le-Vent |Adelie Land|Ile Crozet|Iles Kerguelen|Iles Saint-Paul et Amsterdam |Estuaire|Haut-Ogooue|Moyen-Ogooue|Ngounie|" +
                "Nyanga|Ogooue-Ivindo|Ogooue-Lolo|Ogooue-Maritime|Woleu-Ntem |Banjul|Central River|Lower River|North Bank|Upper River|Western |Gaza Strip |" +
                "Abashis|Abkhazia or Ap'khazet'is Avtonomiuri Respublika (Sokhumi)|Adigenis|Ajaria or Acharis Avtonomiuri Respublika (Bat'umi)|Akhalgoris|" +
                "Akhalk'alak'is|Akhalts'ikhis|Akhmetis|Ambrolauris|Aspindzis|Baghdat'is|Bolnisis|Borjomis|Ch'khorotsqus|Ch'okhatauris|Chiat'ura|Dedop'listsqaros|" +
                "Dmanisis|Dushet'is|Gardabanis|Gori|Goris|Gurjaanis|Javis|K'arelis|K'ut'aisi|Kaspis|Kharagaulis|Khashuris|Khobis|Khonis|Lagodekhis|Lanch'khut'is|" +
                "Lentekhis|Marneulis|Martvilis|Mestiis|Mts'khet'is|Ninotsmindis|Onis|Ozurget'is|P'ot'i|Qazbegis|Qvarlis|Rust'avi|Sach'kheris|Sagarejos|Samtrediis|" +
                "Senakis|Sighnaghis|T'bilisi|T'elavis|T'erjolis|T'et'ritsqaros|T'ianet'is|Tqibuli|Ts'ageris|Tsalenjikhis|Tsalkis|Tsqaltubo|Vanis|Zestap'onis|Zugdidi|" +
                "Zugdidis |Baden-Wuerttemberg|Bayern|Berlin|Brandenburg|Bremen|Hamburg|Hessen|Mecklenburg-Vorpommern|Niedersachsen|Nordrhein-Westfalen|Rheinland-Pfalz|" +
                "Saarland|Sachsen|Sachsen-Anhalt|Schleswig-Holstein|Thueringen |Ashanti|Brong-Ahafo|Central|Eastern|Greater Accra|Northern|Upper East|Upper West|Volta|" +
                "Western |Gibraltar |Ile du Lys|Ile Glorieuse |Aitolia kai Akarnania|Akhaia|Argolis|Arkadhia|Arta|Attiki|Ayion Oros (Mt. Athos)|Dhodhekanisos|Drama|" +
                "Evritania|Evros|Evvoia|Florina|Fokis|Fthiotis|Grevena|Ilia|Imathia|Ioannina|Irakleion|Kardhitsa|Kastoria|Kavala|Kefallinia|Kerkyra|Khalkidhiki|Khania|" +
                "Khios|Kikladhes|Kilkis|Korinthia|Kozani|Lakonia|Larisa|Lasithi|Lesvos|Levkas|Magnisia|Messinia|Pella|Pieria|Preveza|Rethimni|Rodhopi|Samos|Serrai|" +
                "Thesprotia|Thessaloniki|Trikala|Voiotia|Xanthi|Zakinthos |Avannaa (Nordgronland)|Kitaa (Vestgronland)|Tunu (Ostgronland) |Carriacou and Petit Martinique|" +
                "Saint Andrew|Saint David|Saint George|Saint John|Saint Mark|Saint Patrick |Basse-Terre|Grande-Terre|Iles de la Petite Terre|Iles des Saintes|Marie-Galante|" +
                "Guam |Alta Verapaz|Baja Verapaz|Chimaltenango|Chiquimula|El Progreso|Escuintla|Guatemala|Huehuetenango|Izabal|Jalapa|Jutiapa|Peten|Quetzaltenango|Quiche|" +
                "Retalhuleu|Sacatepequez|San Marcos|Santa Rosa|Solola|Suchitepequez|Totonicapan|Zacapa |Castel|Forest|St. Andrew|St. Martin|St. Peter Port|St. Pierre du Bois|" +
                "St. Sampson|St. Saviour|Torteval|Vale |Beyla|Boffa|Boke|Conakry|Coyah|Dabola|Dalaba|Dinguiraye|Dubreka|Faranah|Forecariah|Fria|Gaoual|Gueckedou|Kankan|" +
                "Kerouane|Kindia|Kissidougou|Koubia|Koundara|Kouroussa|Labe|Lelouma|Lola|Macenta|Mali|Mamou|Mandiana|Nzerekore|Pita|Siguiri|Telimele|Tougue|Yomou |Bafata|" +
                "Biombo|Bissau|Bolama-Bijagos|Cacheu|Gabu|Oio|Quinara|Tombali |Barima-Waini|Cuyuni-Mazaruni|Demerara-Mahaica|East Berbice-Corentyne|" +
                "Essequibo Islands-West Demerara|Mahaica-Berbice|Pomeroon-Supenaam|Potaro-Siparuni|Upper Demerara-Berbice|Upper Takutu-Upper Essequibo|" +
                "Artibonite|Centre|Grand'Anse|Nord|Nord-Est|Nord-Ouest|Ouest|Sud|Sud-Est |Heard Island and McDonald Islands |Holy See (Vatican City|" +
                "Atlantida|Choluteca|Colon|Comayagua|Copan|Cortes|El Paraiso|Francisco Morazan|Gracias a Dios|Intibuca|Islas de la Bahia|La Paz|Lempira|" +
                "Ocotepeque|Olancho|Santa Barbara|Valle|Yoro|Hong Kong|Howland Island|Bacs-Kiskun|Baranya|Bekes|Bekescsaba|Borsod-Abauj-Zemplen|Budapest|" +
                "Csongrad|Debrecen|Dunaujvaros|Eger|Fejer|Gyor|Gyor-Moson-Sopron|Hajdu-Bihar|Heves|Hodmezovasarhely|Jasz-Nagykun-Szolnok|Kaposvar|Kecskemet|" +
                "Komarom-Esztergom|Miskolc|Nagykanizsa|Nograd|Nyiregyhaza|Pecs|Pest|Somogy|Sopron|Szabolcs-Szatmar-Bereg|Szeged|Szekesfehervar|Szolnok|Szombathely|" +
                "Tatabanya|Tolna|Vas|Veszprem|Veszprem|Zala|Zalaegerszeg|Akranes|Akureyri|Arnessysla|Austur-Bardhastrandarsysla|Austur-Hunavatnssysla|Austur-Skaftafellssysla|" +
                "Borgarfjardharsysla|Dalasysla|Eyjafjardharsysla|Gullbringusysla|Hafnarfjordhur|Husavik|Isafjordhur|Keflavik|Kjosarsysla|Kopavogur|Myrasysla|" +
                "Neskaupstadhur|Nordhur-Isafjardharsysla|Nordhur-Mulasys-la|Nordhur-Thingeyjarsysla|Olafsfjordhur|Rangarvallasysla|Reykjavik|Saudharkrokur|" +
                "Seydhisfjordhur|Siglufjordhur|Skagafjardharsysla|Snaefellsnes-og Hnappadalssysla|Strandasysla|Sudhur-Mulasysla|Sudhur-Thingeyjarsysla|" +
                "Vesttmannaeyjar|Riyadh|Kuwait|Doha|Muscat|Vestur-Bardhastrandarsysla|Vestur-Hunavatnssysla|Vestur-Isafjardharsysla|Vestur-Skaftafellssysla|Andaman and Nicobar Islands|" +
                "Andhra Pradesh|Arunachal Pradesh|Jedah|Mecca|Damascus|Assam|Bihar|Chandigarh|Chhattisgarh|Dadra and Nagar Haveli|Daman and Diu|Delhi|Goa|Gujarat|Haryana|" +
                "Himachal Pradesh|Jammu and Kashmir|Jharkhand|Karnataka|Kerala|Lakshadweep|Madhya Pradesh|Maharashtra|Manipur|Meghalaya|Mizoram|Nagaland|Orissa|" +
                "Pondicherry|Jerusalem|Ankara|Sanaa|Abu Dhabe|Punjab|Baghdad|Rajasthan|Sikkim|Tamil Nadu|Tripura|Uttar Pradesh|Uttaranchal|West Bengal"*/


        var cityString = "Abashis|Abengourou|Abidjan|Abkhazia|Aboisso|Abseron Rayonu|Abu Dhabe|Acklins and Crooked Islands|Acre|Ad Daqahliyah|Adamaoua|Adelie Land|Adiake|Adigenis|Adis Abeba (Addis Ababa)|Adrar|Adzope|Afar|Agboville|Agcabadi Rayonu|Agdam Rayonu|Agdas Rayonu|Agnibilekrou|Agstafa Rayonu|Agsu Rayonu|Ahuachapan|Ain Defla|Ain Temouchent|Aisen del General Carlos Ibanez del Campo|Aitolia kai Akarnania|Aitutaki|Ajaria or Acharis Avtonomiuri Respublika (Bat'umi)|Akale Guzay|Akhaia|Akhalgoris|Akhalk'alak'is|Akhalts'ikhis|Akhmetis|Akranes|Akureyri|Al Bahr al Ahmar|Al Buhayrah|Al Fayyum|Al Gharbiyah|Al Hadd|Al Iskandariyah|Al Isma'iliyah|Al Jizah|Al Manamah|Al Mintaqah al Gharbiyah|Al Mintaqah al Wusta|Al Mintaqah ash Shamaliyah|Al Minufiyah|Al Minya|Al Muharraq|Al Qahirah|Al Qalyubiyah|Al Wadi al Jadid|Alagoas|Alajuela|Aland|Alberta|Ale'pe'|Alger|Ali Bayramli Sahari|Alibori|Alsace|Alta Verapaz|Amapa|Amara|Amazonas|Amazonas|Ambrolauris|Amman|Andaman and Nicobar Islands|Andhra Pradesh|Andorra la Vella|Anegada|Anguilla|Anhui|Anjouan (Nzwani)|Ankara|Annaba|Annobon|Antartica|Antartica e Islas del Atlantico Sur|Antioquia|Antofagasta|Antwerpen|Aqaba|Aquitaine|Ar Rifa' wa al Mintaqah al Janubiyah|Aragatsotn|Ararat|Arauca|Araucania|Archipel des Marquises|Archipel des Tuamotu|Archipel des Tubuai|Argolis|Arhus|Arkadhia|Armavir|Arnessysla|Arta|Artibonite|Aruba|Arunachal Pradesh|As Suways|Ash Sharqiyah|Ashanti|Ashmore and Cartier Island|Aspindzis|Assam|Astara Rayonu|Aswan|Asyut|Atacama|Atakora|Atiu|Atlantico|Atlantida|Atlantique|Attiki|Australian Capital Territory|Austur-Bardhastrandarsysla|Austur-Hunavatnssysla|Austur-Skaftafellssysla|Auvergne|Avannaa (Nordgronland)|Avarua|Ayeyarwady|Ayion Oros (Mt. Athos)|Azua|Azuay|Bacs-Kiskun|Badakhshan|Baden-Wuerttemberg|Badghis|Bafata|Bagerhat|Baghdad|Baghdat'is|Baghlan|Bago|Bahia|Baja Verapaz|Baki Sahari|Balakan Rayonu|Bale|Balkh|Bam|Bamian|Bamingui-Bangoran|Bandarban|Bandundu|Bangolo|Bangui|Bani Suwayf|Banjul|Banteay Mean Cheay|Banwa|Baoruco|Barahona|Baranya|Barbuda|Barda Rayonu|Barguna|Barima-Waini|Barisal|Barka|Bas-Congo|Basse-Kotto|Basse-Normandie|Basse-Terre|Batdambang|Batha|Batna|Bayern|Bazega|Bechar|Beijing|Beirut|Bejaia|Bekes|Bekescsaba|Belait|Belize|Bengo|Benguela|Beni|Beoumi|Berat|Berlin|Beyla|Beylaqan Rayonu|Bhola|Biankouma|Bie|Bihar|Bilasuvar Rayonu|Biltine|Bimini|Bio-Bio|Bioko Norte|Bioko Sur|Biombo|Biskra|Bissau|Bjelovarsko-Bilogorska Zupanija|Blagoevgrad|Blida|Boa Vista|Bocanda|Boffa|Bogra|Boke|Bolama-Bijagos|Bolivar|Bolivar|Bolnisis|Bondoukou|Bongouanou|Bordj Bou Arreridj|Bordoy|Borgou|Borjomis|Borkou-Ennedi-Tibesti|Bornholm|Borsod-Abauj-Zemplen|Bouafle|Bouake|Bouenza|Bougouriba|Bouira|Boulgou|Boulkiemde|Boumerdes|Bouna|Boundiali|Bourgogne|Boyaca|Brabant Wallon|Brahmanbaria|Brandenburg|Brava|Brazzaville|Bremen|Brestskaya (Brest)|Bretagne|Bridgetown|British Columbia|Brnensky|Brodsko-Posavska Zupanija|Brong-Ahafo|Brunei and Muara|Brussels Capitol Region|Bubanza|Budapest|Budejovicky|Buenos Aires|Buenos Aires Capital Federal|Bujumbura|Bulqize|Bumthang|Bur Sa'id|Burgas|Burgenland|Bururi|Cabanas|Cabinda|Cabrayil Rayonu|Cacheu|Cairo|Caldas|Calilabad Rayonu|Camaguey|Canar|Canillo|Cankuzo|Caqueta|Carchi|Carriacou and Petit Martinique|Cartago|Casanare|Castel|Cat Island|Catamarca|Cauca|Cayo|Ceara|Central|Central|Central|Central River|Centre|Centre|Centre|Centro Sur|Cesar|Ch'khorotsqus|Ch'okhatauris|Chaco|Chalatenango|Champagne-Ardenne|Chandigarh|Chandpur|Chari-Baguirmi|Chhattisgarh|Chhukha|Chiat'ura|Chimaltenango|Chimborazo|Chin State|Chiquimula|Chirang|Chittagong|Chlef|Chobe|Choco|Choluteca|Chongqing|Christ Church|Christmas Island|Chuadanga|Chubut|Chuquisaca|Cibitoke|Ciego de Avila|Cienfuegos|Ciudad de La Habana|Clipperton Island|Cochabamba|Collines|Colon|Comayagua|Comilla|Comoe|Conakry|Constantine|Copan|Coquimbo|Cordoba|Cordoba|Corozal|Corrientes|Corse|Cortes|Cotopaxi|Couffo|Cox's Bazar|Coyah|Creek|Csongrad|Cuando Cubango|Cuanza Norte|Cuanza Sul|Cundinamarca|Cunene|Cuscatlan|Cuvette|Cuyuni-Mazaruni|Dabakala|Dabola|Dabon|Dadra and Nagar Haveli|Daga|Dajabon|Dalaba|Dalasysla|Daloa|Daman and Diu|Damascus|Danane|Daoukro|Daskasan Rayonu|Davaci Rayonu|Debrecen|Dedop'listsqaros|Delhi|Delvine|Demerara-Mahaica|Denkel|Devoll (Bilisht)|Devonshire|Dhaka|Dhodhekanisos|Diber (Peshkopi)|Dikhil|Dimbokro|Dinajpur|Dinguiraye|Dire Dawa|Direction Island|Distrito Capital de Santa Fe de Bogota|Distrito Federal|Distrito Nacional|Divo|Djelfa|Djibouti|Dmanisis|Dobrich|Doha|Domoni|Donga|Drama|Duarte|Dubreka|Dubrovacko-Neretvanska Zupanija|Duekoue|Dumyat|Dunaujvaros|Durres|Dushet'is|East Berbice-Corentyne|Eastern|Eastern|Eastern|Eastern|Eger|El Bayadh|El Oro|El Oued|El Paraiso|El Progreso|El Seibo|El Tarf|Elbasan|Elias Pina|Encamp|Entre Rios|Equateur|Escaldes-Engordany|Escuintla|Esmeraldas|Espaillat|Espirito Santo|Essequibo Islands-West Demerara|Est|Estuaire|Etela-Suomen Laani|Europa Island |Evritania|Evros|Evvoia|Extreme-Nord|Exuma|Eyjafjardharsysla|Eysturoy|Falkland Islands (Islas Malvinas) |Famagusta|Farah|Faranah|Faridpur|Faryab|Federation of Bosnia and Herzegovina|Fejer|Feni|Ferkessedougou|Fier|Florina|Fokis|Fomboni|Forecariah|Forest|Formosa|Franche-Comte|Francisco Morazan|Francistown|Fredericksberg|Frederiksborg|Freeport|French Guiana |Fresh Creek|Fria|Fthiotis|Fujian|Fuzuli Rayonu|Fyn|Gaborone|Gabrovo|Gabu|Gadabay Rayonu|Gagnoa|Gaibandha|Galapagos|Gambela Hizboch|Ganca Sahari|Ganzourgou|Gaoual|Gardabanis|Gaza Strip |Gazipur|Geghark'unik'|Geylegphug|Ghanzi|Ghardaia|Ghazni|Ghowr|Gibraltar |Gitega|Gjirokaster|Gnagna|Goa|Goias|Gopalganj|Goranboy Rayonu|Gori|Goris|Gourma|Governor's Harbour|Goycay Rayonu|Gracias a Dios|Gramsh|Grand Bassam|Grand'Anse|Grand-Lahou|Grande Comore (Njazidja)|Grande-Terre|Granma|Greater Accra|Green Turtle Cay|Grevena|Gribingui|Guainia|Guam |Guanacaste|Guangdong|Guangxi|Guantanamo|Guatemala|Guaviare|Guayas|Gueckedou|Guelma|Guera|Guiglo|Guizhou|Gujarat|Gullbringusysla|Gurjaanis|Gyor|Gyor-Moson-Sopron|Ha|Habiganj|Haciqabul Rayonu|Hafnarfjordhur|Hainan|Hainaut|Hajdu-Bihar|Hamasen|Hamburg|Hamilton|Hamilton|Harbour Island|Hareri Hizb|Harjumaa (Tallinn)|Haryana|Has (Krume)|Hato Mayor|Haut-Mbomou|Haut-Ogooue|Haute-Kotto|Haute-Normandie|Haute-Sangha|Heard Island and McDonald Islands |Hebei|Heilongjiang|Helmand|Henan|Herat|Heredia|Hessen|Heves|High Rock|Hiiumaa (Kardla)|Himachal Pradesh|Hodmezovasarhely|Holguin|Holy See (Vatican City|Home Island|Homyel'skaya (Homyel')|Hong Kong|Horad Minsk|Horsburgh Island|Houet|Howland Island|Hrodzyenskaya (Hrodna)|Huambo|Hubei|Huehuetenango|Huila|Huila|Hunan|Husavik|Ida-Virumaa (Johvi)|Ile Crozet|Ile Glorieuse |Ile du Lys|Ile-de-France|Iles Kerguelen|Iles Saint-Paul et Amsterdam |Iles Sous-le-Vent |Iles de la Petite Terre|Iles des Saintes|Iles du Vent|Ilia|Illizi|Imathia|Imbabura|Imisli Rayonu|Inagua|Independencia|Intibuca|Ioannina|Ioba|Irakleion|Irbid|Isafjordhur|Isla de la Juventud|Islas de la Bahia|Issia|Istarska Zupanija|Ita-Suomen Laani|Izabal|Jacqueville|Jaipurhat|Jalapa|Jamalpur|Jammu and Kashmir|Janub Sina'|Jarvamaa (Paide)|Jasz-Nagykun-Szolnok|Javis|Jedah|Jerusalem|Jessore|Jhalokati|Jharkhand|Jhenaidah|Jiangsu|Jiangxi|Jidd Hafs|Jihlavsky|Jijel|Jilin|Jogevamaa (Jogeva)|Jost Van Dyke|Jowzjan|Jujuy|Jutiapa|Juzur Hawar|K'arelis|K'ut'aisi|Kabol|Kachin State|Kadiogo|Kaernten|Kafr ash Shaykh|Kalbacar Rayonu|Kampong Cham|Kampong Chhnang|Kampong Spoe|Kampong Thum|Kampot|Kandahar|Kandal|Kanem|Kankan|Kaoh Kong|Kapisa|Kaposvar|Karak|Kardhitsa|Karlovacka Zupanija|Karlovarsky|Karnataka|Karuzi|Kasai-Occidental|Kasai-Oriental|Kaspis|Kastoria|Katanga|Katiola|Kavaje|Kavala|Kayah State|Kayanza|Kayin State|Keb|Kecskemet|Kefallinia|Keflavik|Kemo-Gribingui|Kemps Bay|Kenedougou|Kerala|Kerkyra|Kerouane|Kgalagadi|Kgatleng|Khagrachari|Khalkidhiki|Khania|Kharagaulis|Khashuris|Khaskovo|Khenchela|Khios|Khobis|Khonis|Khulna|Kie-Ntem|Kikladhes|Kilkis|Kindia|Kinshasa|Kirundo|Kishoreganj|Kissidougou|Kitaa (Vestgronland)|Kjosarsysla|Kobenhavn|Kobenhavns|Kolonje (Erseke)|Komandjari|Komarom-Esztergom|Kompienga|Konar|Kondoz|Kopavogur|Koprivnicko-Krizevacka Zupanija|Korce|Korhogo|Korinthia|Kossi|Kotayk'|Koubia|Kouilou|Koundara|Koupelogo|Kouritenga|Kouroussa|Kourweogo|Kozani|Kracheh|Kralovehradecky|Krapinsko-Zagorska Zupanija|Kruje|Kucove|Kukes|Kurbin|Kurdamir Rayonu|Kurdzhali|Kurigram|Kushtia|Kuwait|Kweneng|Kyrenia|Kyustendil|La Altagracia|La Guajira|La Habana|La Libertad|La Massana|La Pampa|La Paz|La Paz|La Paz|La Rioja|La Romana|La Union|La Vega|Laane-Virumaa (Rakvere)|Laanemaa (Haapsalu)|Labe|Lac|Lacin Rayonu|Laghman|Laghouat|Lagodekhis|Lakonia|Lakota|Lakshadweep|Lakshmipur|Lalmonirhat|Lanch'khut'is|Languedoc-Roussillon|Lankaran Rayonu|Lankaran Sahari|Lansi-Suomen Laani|Lappi|Larisa|Larnaca|Las Tunas|Lasithi|Lekoumou|Lelouma|Lempira|Lentekhis|Leraba|Lerik Rayonu|Lesvos|Levkas|Lezhe|Lhuntshi|Liaoning|Liberecky|Libertador General Bernardo O'Higgins|Librazhd|Licko-Senjska Zupanija|Liege|Likouala|Limassol|Limburg|Limon|Limousin|Litoral|Littoral|Littoral|Lobatse|Lobaye|Logone Occidental|Logone Oriental|Loja|Lola|Long Island|Loroum|Lorraine|Lorri|Los Lagos|Los Rios|Lovech|Lower River|Lowgar|Luanda|Lunda Norte|Lunda Sul|Lushnje|Luxembourg|M'Sila|Macenta|Madaripur|Madhya Pradesh|Madinat 'Isa|Madinat Hamad|Magallanes y de la Antartica Chilena|Magdalena|Magnisia|Magura|Magway|Mahaica-Berbice|Maharashtra|Mahilyowskaya (Mahilyow)|Maio|Makamba|Malanje|Malesi e Madhe (Koplik)|Mali|Mallakaster (Ballsh)|Mamou|Man|Manabi|Mandalay|Mandiana|Mangaia|Maniema|Manihiki|Manikganj|Manipur|Manitoba|Mankono|Manu'a|Manuae|Maranhao|Maria Trinidad Sanchez|Marie-Galante|Marneulis|Marsh Harbour|Martvilis|Masalli Rayonu|Mascara|Mat (Burrel)|Matanzas|Mato Grosso|Mato Grosso do Sul|Matruh|Mauke|Maule|Maulvi bazar|Mayaguana|Mayo-Kebbi|Mbahiakro|Mbomou|Mecca|Mecklenburg-Vorpommern|Medea|Medimurska Zupanija|Meghalaya|Meherpur|Mendoza|Messinia|Mestiis|Meta|Midi-Pyrenees|Midland|Mila|Minas Gerais|Mingacevir Sahari|Minskaya|Mirdite (Rreshen)|Misiones|Miskolc|Mitiaro|Mizoram|Moheli (Mwali)|Mon State|Mondol Kiri|Mongar|Mono|Monsenor Nouel|Montana|Monte Cristi|Monte Plata|Morazan|Morona-Santiago|Moroni|Mostaganem|Mosteiros|Mouhoun|Moutsamoudou |Moxico|Moyen-Chari|Moyen-Ogooue|Mts'khet'is|Munshiganj|Muramvya|Muscat|Muyinga|Mwaro|Mykines|Mymensingh|Myrasysla|Naama|Naftalan Sahari|Nagaland|Nagykanizsa|Nahouri|Namentenga|Namibe|Namur|Nana-Mambere|Nangarhar|Naogaon|Napo|Narail|Narayanganj|Narino|Narsingdi|Nassau Island|Natore|Naumbiel|Nawabganj|Naxcivan Muxtar Respublikasi|Nayala|Neftcala Rayonu|Nei Mongol|Neskaupstadhur|Netrokona|Neuquen|New Brunswick|New Providence|New South Wales|Newfoundland|Ngamiland|Ngounie|Ngozi|Niari|Nicholls Town and Berry Islands|Nicosia|Niederoesterreich|Niedersachsen|Nilphamari|Nimruz|Ningxia|Ninotsmindis|Noakhali|Nograd|Nord|Nord|Nord-Est|Nord-Kivu|Nord-Ouest|Nord-Ouest|Nord-Pas-de-Calais|Nordhur-Isafjardharsysla|Nordhur-Mulasys-la|Nordhur-Thingeyjarsysla|Nordjylland|Nordrhein-Westfalen|Norte de Santander|North Bank|North Keeling Island|North-East|Northern|Northern|Northern Territory|Northwest Territories|Nova Scotia|Nunavut|Nyanga|Nyiregyhaza|Nzerekore|Oberoesterreich|Obock|Ocotepeque|Odienne|Ogooue-Ivindo|Ogooue-Lolo|Ogooue-Maritime|Oguz Rayonu|Oio|Olafsfjordhur|Olancho|Olomoucky|Ombella-Mpoko|Onis|Ontario|Oost-Vlaanderen|Oran|Orange Walk|Ordino|Orellana|Orientale|Orissa|Oromiya|Oruro|Oruzgan|Osjecko-Baranjska Zupanija|Ostravsky|Otdar Mean Cheay|Ouaddai|Ouaka|Ouargla|Oubritenga|Oudalan|Oueme|Ouest|Ouest|Ouham|Ouham-Pende|Oulun Laani |Oum el Bouaghi|Oume|Ozurget'is|P'ot'i|Pabna|Paget|Pailin|Paktia|Paktika|Palmerston|Panchagarh|Pando|Paphos |Para|Paraiba|Parana|Pardubicky|Parnumaa (Parnu)|Paro|Parvan|Passore|Pastaza|Patuakhali|Paul|Pays de la Loire|Pazardzhik|Pecs|Pedernales|Pella|Pemagatsel|Pembroke|Penrhyn|Peqin|Peravia|Permet|Pernambuco|Pernik|Pest|Peten|Phnum Penh|Piaui|Picardie|Pichincha|Pieria|Pinar del Rio|Pirojpur|Pita|Plateau|Plateaux|Pleven|Plovdiv|Plzensky|Pogradec|Poitou-Charentes|Polvamaa (Polva)|Pomeroon-Supenaam|Pondicherry|Poni|Pool|Porto Novo|Potaro-Siparuni|Potosi|Pouthisat|Pozesko-Slavonska Zupanija|Praha|Praia|Preah Seihanu (Sihanoukville)|Preah Vihear|Preveza|Prey Veng|Primorsko-Goranska Zupanija|Prince Edward Island|Provence-Alpes-Cote d'Azur|Puerto Plata|Pukapuka|Puke|Punakha|Punjab|Puntarenas|Putumayo|Qabala Rayonu|Qax Rayonu|Qazax Rayonu|Qazbegis|Qina|Qinghai|Qobustan Rayonu|Quba Rayonu|Qubadli Rayonu|Quebec|Queensland|Quetzaltenango|Quiche|Quinara|Quindio|Qusar Rayonu|Qvarlis|Ragged Island|Rajasthan|Rajbari|Rajshahi|Rakahanga|Rakhine State|Rangamati|Rangarvallasysla|Rangpur|Raplamaa (Rapla)|Rarotonga|Razgrad|Redonda|Region Metropolitana (Santiago)|Relizane|Republika Srpska|Retalhuleu|Rethimni|Reykjavik|Rheinland-Pfalz|Rhone-Alpes |Ribe|Ribeira Grande|Ringkobing|Rio Grande do Norte|Rio Grande do Sul|Rio Negro|Rio de Janeiro|Risaralda|Riyadh|Rock Sound|Rodhopi|Rondonia|Roraima|Rose Island|Roskilde|Rotanah Kiri|Rotuma|Ruse|Rust'avi|Rutana|Ruyigi|Saaremaa (Kuessaare)|Saarland|Saatli Rayonu|Sabirabad Rayonu|Sacatepequez|Sach'kheris|Sachsen|Sachsen-Anhalt|Sagaing|Sagarejos|Sahil|Saida|Saint Andrew|Saint Andrew|Saint Andrew|Saint David|Saint David|Saint George|Saint George|Saint George|Saint George|Saint George|Saint Georges|Saint James|Saint John|Saint John|Saint John|Saint John|Saint Joseph|Saint Joseph|Saint Lucy|Saint Luke|Saint Mark|Saint Mark|Saint Mary|Saint Michael|Saint Patrick|Saint Patrick |Saint Paul|Saint Paul|Saint Peter|Saint Peter|Saint Peter |Saint Philip|Saint Philip|Saint Thomas|Sakassou|Saki Rayonu|Saki Sahari|Sal|Salamat|Salcedo|Salt|Salta|Salyan Rayonu|Salzburg|Samana|Samangan|Samaxi Rayonu|Samchi|Samdrup Jongkhar|Samentenga|Samkir Rayonu|Samos|Samtrediis|Samux Rayonu|San Andres y Providencia|San Cristobal|San Jose |San Juan|San Juan|San Luis|San Marcos|San Miguel|San Pedro de Macoris|San Salvador|San Salvador and Rum Cay|San Vicente|San-Pedro|Sanaa|Sanchez Ramirez|Sancti Spiritus|Sandoy|Sandy Point|Sandys|Sangha|Sangha |Sanguie|Sant Julia de Loria|Santa Ana|Santa Barbara|Santa Catarina|Santa Catarina|Santa Cruz|Santa Cruz|Santa Cruz|Santa Fe|Santa Rosa|Santander|Santiago|Santiago Rodriguez|Santiago de Cuba|Santiago del Estero|Sao Domingos|Sao Filipe|Sao Nicolau|Sao Paulo|Sao Vicente|Sar-e Pol|Sarande|Saskatchewan|Sassandra|Satkhira|Saudharkrokur|Schleswig-Holstein|Seguela|Selebi-Pikwe|Semhar|Senakis|Senhit|Seno|Seraye |Sergipe|Serrai|Setif|Seydhisfjordhur|Shaanxi|Shamal Sina'|Shan State|Shandong|Shanghai|Shanxi|Shariatpur|Shemgang|Sherpur|Shirak|Shkoder|Shumen|Sibensko-Kninska Zupanija|Sichuan|Sidi Bel Abbes|Siem Reab|Sighnaghis|Siglufjordhur|Siguiri|Sikkim|Silistra|Sinfra|Sirajganj|Sisacko-Moslavacka Zupanija|Sissili|Sitrah|Siyazan Rayonu|Skagafjardharsysla|Skikda|Skrapar (Corovode)|Skuvoy|Sliven|Smiths|Smolyan|Snaefellsnes-og Hnappadalssysla|Sofiya|Sofiya-Grad|Solola|Somogy|Sonderjylland|Sonsonate|Sopron|Soubre|Souk Ahras|Soum|Sourou|South Australia|South Island|South Town|South-East|Southampton|Southern|Splitsko-Dalmatinska Zupanija|Spot Bay|St. Andrew|St. Martin|St. Peter Port|St. Pierre du Bois|St. Sampson|St. Saviour|Stake Bay|Stann Creek|Stara Zagora|Steiermark|Stoeng Treng|Storstrom|Strandasysla|Stredocesky|Streymoy|Suchitepequez|Sucre|Sucumbios|Sud|Sud|Sud-Est |Sud-Kivu |Sud-Ouest|Sudhur-Mulasysla|Sudhur-Thingeyjarsysla|Suduroy|Suhaj |Sumale|Sumqayit Sahari|Sunamganj|Susa Rayonu|Susa Sahari|Suwarrow|Svay Rieng|Swains Island|Sylhet|Syunik'|Szabolcs-Szatmar-Bereg|Szeged|Szekesfehervar|Szolnok|Szombathely|T'bilisi|T'elavis|T'erjolis|T'et'ritsqaros|T'ianet'is|Tabou|Tadjoura |Takev|Takhar|Takutea |Tamanghasset|Tamil Nadu|Tanda|Tandjile|Tangail|Tanintharyi|Tapoa|Tarapaca|Tarija|Tarrafal|Tartar Rayonu|Tartumaa (Tartu)|Tashigang|Tasmania|Tatabanya|Tavush|Tebessa|Telimele|Temburong|Tepelene|Thakurgaon|Thesprotia|Thessaloniki|Thimphu|Thueringen |Tianjin|Tiaret|Tiassale|Tiebissou|Tierra del Fuego|Tigray|Tindouf|Tingrela|Tipaza|Tirane (Tirana)|Tirane (Tirana)|Tirol|Tissemsilt|Tizi Ouzou|Tlemcen|Tocantins|Toledo|Tolima|Tolna|Tombali |Tongsa|Torteval|Tortola|Totonicapan|Touba|Tougue|Toulepleu|Toumodi|Tovuz Rayonu|Tqibuli|Trikala|Tripura|Tropoje (Bajram Curri)|Ts'ageris|Tsalenjikhis|Tsalkis|Tsqaltubo|Tucuman|Tungurahua|Tunu (Ostgronland) |Turgovishte|Tutong|Tuy|Tvoroyri|Ucar Rayonu|Uige|Upper Demerara-Berbice|Upper East|Upper River|Upper Takutu-Upper Essequibo|Upper West|Ustecky|Usulutan |Uttar Pradesh|Uttaranchal|Vagar |Vakaga|Vale |Valgamaa (Valga)|Valle|Valle del Cauca|Valparaiso|Valverde|Vanis|Varazdinska Zupanija|Vardak|Varna|Vas|Vaupes|Vavoua|Vayots' Dzor|Vejle|Veliko Turnovo|Vestsjalland|Vesttmannaeyjar|Vestur-Bardhastrandarsysla|Vestur-Hunavatnssysla|Vestur-Isafjardharsysla|Vestur-Skaftafellssysla|Veszprem|Veszprem|Viborg |Vichada|Victoria|Vidin|Viljandimaa (Viljandi)|Villa Clara |Virgin Gorda|Viroviticko-Podravska Zupanija|Vitsyebskaya (Vitsyebsk)|Vlaams Brabant|Vlore|Voiotia|Volta|Vorarlberg|Vorumaa (Voru) |Vratsa|Vukovarsko-Srijemska Zupanija|Wangdi Phodrang|Warwick|Wele-Nzas |West Bengal|West End|West Island|West-Vlaanderen|Western|Western|Western |Western |Western |Western Australia|Wien|Woleu-Ntem |Xacmaz Rayonu|Xankandi Sahari|Xanlar Rayonu|Xanthi|Xinjiang|Xizang (Tibet)|Xizi Rayonu|Xocali Rayonu|Xocavand Rayonu|Yagha|Yambol|Yamoussoukro|Yangon|Yardimli Rayonu|Yatenga|YeDebub Biheroch Bihereseboch na Hizboch |Yerevan|Yevlax Rayonu|Yevlax Sahari|Yomou |Yoro|Yukon Territory|Yunnan|Zabol|Zacapa |Zadarska Zupanija|Zagreb|Zagrebacka Zupanija|Zaire|Zakinthos |Zala|Zalaegerszeg|Zamora-Chinchipe |Zangilan Rayonu|Zaqatala Rayonu|Zardab Rayonu|Zarqa|Zestap'onis|Zhejiang|Ziro|Zlinsky |Zondomo|Zou|Zoundweogo|Zuenoula |Zugdidi|Zugdidis"

        var cityArray = cityString.split("|").toTypedArray()



        address_signup.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //val addressArray = arrayOf("Amman","London")

            builder.setTitle("Select your address")
            builder.setSingleChoiceItems(cityArray, -1){ dialog: DialogInterface, which: Int ->
                register_address.setText(cityArray[which])

            }
            builder.setPositiveButton("OK"){dialog, which ->
                //database
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                register_address.setText("")
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }


        radioButton3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                background.setBackgroundResource(R.drawable.gradient_male)

            }
        })

        radioButton5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                background.setBackgroundResource(R.drawable.gradient_female)

            }
        })

        register_button.setOnClickListener {


            if(register_email.text.toString() == "" || register_pass.text.toString() == "" || register_confirm.text.toString() == ""
                || register_address.text.toString() == "" ||  register_name.text.toString() == "" ){
                Toast.makeText(this,"Fields must not be empty!",
                    Toast.LENGTH_LONG).show()
            }
            else if (register_pass.text.toString() != register_confirm.text.toString()) {
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_LONG).show()
            } else if (register_pass.text.toString().length < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_LONG).show()

            }else{


                var pd = ProgressDialog(this)
                pd.setMessage("Please Wait...")
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                pd.show()

                var rq = Volley.newRequestQueue(this)

                var jo = JsonObjectRequest(Request.Method.GET,
                    "https://api.emailverifyapi.com/v3/lookups/json?key=512BE052EAFFBB11&email="+register_email.text.toString(),
                    null,Response.Listener {
                        response ->
                            if(response.getString("deliverable") != "true")
                            {
                                pd.hide()
                                tv_hidden.text = "ok"
                                Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
                            }



                    },Response.ErrorListener {
                        error ->
                        pd.hide()
                        Toast.makeText(
                            this, error.message,
                            Toast.LENGTH_LONG
                        ).show()
                    })

                rq.add(jo)

                if(tv_hidden.text.toString() == "ok"){
                    tv_hidden.text = ""
                    var rq2 = Volley.newRequestQueue(this)
                    var sr = object : StringRequest(
                    Request.Method.POST, AppInfo.web + "signup.php",
                    Response.Listener { response ->
                        pd.hide()
                        if(response != "0"){
                            AppInfo.Email = register_email.text.toString()
                            AppInfo.Address = register_address.text.toString()
                            AppInfo.Name = register_name.text.toString()
                            AppInfo.UserID = response
                            if(radioButton5.isChecked){
                                AppInfo.Gender = "0"
                            }
                            else{
                                AppInfo.Gender = "1"
                            }
                            var i = Intent(this, MainActivity::class.java)
                            Toast.makeText(this,"Welcome " + register_name.text.toString() +"!",Toast.LENGTH_LONG).show()
                            startActivity(i)
                            finish()
                        }
                        else if(response == "-1"){
                            Toast.makeText(this,"User already exists",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this,"An error occurred",Toast.LENGTH_LONG).show()
                        }

                    },
                    Response.ErrorListener { error ->
                        pd.hide()
                        Toast.makeText(
                            this, error.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        var map = HashMap<String, String>()
                        if(radioButton5.isChecked){
                            map.put("gender", "0")
                        }
                        else{
                            map.put("gender", "1")
                        }
                        map.put("email", register_email.text.toString())
                        map.put("password", register_pass.text.toString())
                        map.put("name", register_name.text.toString())
                        map.put("address", register_address.text.toString())

                        return map
                    }
                }

                rq2.add(sr)

                }
            }
        }
    }

    fun sort(args: Array<String>) {
        var list = ArrayList<CustomObject>()
        var sortedList = list.sortedWith(compareBy({ it.customProperty }))
        for (obj in sortedList) {
            println(obj.customProperty)
        }
    }
    public class CustomObject(val customProperty: String) {
    }

}
