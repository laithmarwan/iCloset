package com.example.icloset


import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.MultiSelectListPreference
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyAccount : Fragment() {

    lateinit var option : Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_my_account, container, false)
        if(AppInfo.Gender=="0")
        {

            v.gender_toggle.isChecked = false
            v.gender_toggle.textOff = "Female"
            v.gender_toggle.text = "Female"
            v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_female)
        }
        else
        {

            v.gender_toggle.isChecked = true
            v.gender_toggle.textOn = "Male"
            v.gender_toggle.text = "Male"
            v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_male)
        }
        v.gender_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_male)
                v.gender_toggle.text = "Male"
            } else {
                v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_female)
                v.gender_toggle.text = "Female"
            }
        }

        var flag_address = false
        //v.email_view.text = AppInfo.Email
        v.account_name.hint = AppInfo.Name
        v.address.hint = AppInfo.Address

        /*if(AppInfo.Gender == "0"){
            v.gender_toggle.text = "Female"
        }
        else if(AppInfo.Gender == "1"){
            v.gender_toggle.text = "Male"
        }*/

        var opt = ""
       /* var toggle = v.findViewById(R.id.gender_toggle) as ToggleButton
        var result = ""

        option = v.findViewById(R.id.city_spinner) as Spinner
        val options = ArrayList<String>()
        /*options.add(AppInfo.Address)
        *//*options.add("Option1")
        options.add("Option2")*/

        option.adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                opt = option.getItemAtPosition(position).toString()

            }
        }*/

        var cityString = "Abashis|Abengourou|Abidjan|Abkhazia|Aboisso|Abseron Rayonu|Abu Dhabe|Acklins and Crooked Islands|Acre|Ad Daqahliyah|Adamaoua|Adelie Land|Adiake|Adigenis|Adis Abeba (Addis Ababa)|Adrar|Adzope|Afar|Agboville|Agcabadi Rayonu|Agdam Rayonu|Agdas Rayonu|Agnibilekrou|Agstafa Rayonu|Agsu Rayonu|Ahuachapan|Ain Defla|Ain Temouchent|Aisen del General Carlos Ibanez del Campo|Aitolia kai Akarnania|Aitutaki|Ajaria or Acharis Avtonomiuri Respublika (Bat'umi)|Akale Guzay|Akhaia|Akhalgoris|Akhalk'alak'is|Akhalts'ikhis|Akhmetis|Akranes|Akureyri|Al Bahr al Ahmar|Al Buhayrah|Al Fayyum|Al Gharbiyah|Al Hadd|Al Iskandariyah|Al Isma'iliyah|Al Jizah|Al Manamah|Al Mintaqah al Gharbiyah|Al Mintaqah al Wusta|Al Mintaqah ash Shamaliyah|Al Minufiyah|Al Minya|Al Muharraq|Al Qahirah|Al Qalyubiyah|Al Wadi al Jadid|Alagoas|Alajuela|Aland|Alberta|Ale'pe'|Alger|Ali Bayramli Sahari|Alibori|Alsace|Alta Verapaz|Amapa|Amara|Amazonas|Amazonas|Ambrolauris|Amman|Andaman and Nicobar Islands|Andhra Pradesh|Andorra la Vella|Anegada|Anguilla|Anhui|Anjouan (Nzwani)|Ankara|Annaba|Annobon|Antartica|Antartica e Islas del Atlantico Sur|Antioquia|Antofagasta|Antwerpen|Aqaba|Aquitaine|Ar Rifa' wa al Mintaqah al Janubiyah|Aragatsotn|Ararat|Arauca|Araucania|Archipel des Marquises|Archipel des Tuamotu|Archipel des Tubuai|Argolis|Arhus|Arkadhia|Armavir|Arnessysla|Arta|Artibonite|Aruba|Arunachal Pradesh|As Suways|Ash Sharqiyah|Ashanti|Ashmore and Cartier Island|Aspindzis|Assam|Astara Rayonu|Aswan|Asyut|Atacama|Atakora|Atiu|Atlantico|Atlantida|Atlantique|Attiki|Australian Capital Territory|Austur-Bardhastrandarsysla|Austur-Hunavatnssysla|Austur-Skaftafellssysla|Auvergne|Avannaa (Nordgronland)|Avarua|Ayeyarwady|Ayion Oros (Mt. Athos)|Azua|Azuay|Bacs-Kiskun|Badakhshan|Baden-Wuerttemberg|Badghis|Bafata|Bagerhat|Baghdad|Baghdat'is|Baghlan|Bago|Bahia|Baja Verapaz|Baki Sahari|Balakan Rayonu|Bale|Balkh|Bam|Bamian|Bamingui-Bangoran|Bandarban|Bandundu|Bangolo|Bangui|Bani Suwayf|Banjul|Banteay Mean Cheay|Banwa|Baoruco|Barahona|Baranya|Barbuda|Barda Rayonu|Barguna|Barima-Waini|Barisal|Barka|Bas-Congo|Basse-Kotto|Basse-Normandie|Basse-Terre|Batdambang|Batha|Batna|Bayern|Bazega|Bechar|Beijing|Beirut|Bejaia|Bekes|Bekescsaba|Belait|Belize|Bengo|Benguela|Beni|Beoumi|Berat|Berlin|Beyla|Beylaqan Rayonu|Bhola|Biankouma|Bie|Bihar|Bilasuvar Rayonu|Biltine|Bimini|Bio-Bio|Bioko Norte|Bioko Sur|Biombo|Biskra|Bissau|Bjelovarsko-Bilogorska Zupanija|Blagoevgrad|Blida|Boa Vista|Bocanda|Boffa|Bogra|Boke|Bolama-Bijagos|Bolivar|Bolivar|Bolnisis|Bondoukou|Bongouanou|Bordj Bou Arreridj|Bordoy|Borgou|Borjomis|Borkou-Ennedi-Tibesti|Bornholm|Borsod-Abauj-Zemplen|Bouafle|Bouake|Bouenza|Bougouriba|Bouira|Boulgou|Boulkiemde|Boumerdes|Bouna|Boundiali|Bourgogne|Boyaca|Brabant Wallon|Brahmanbaria|Brandenburg|Brava|Brazzaville|Bremen|Brestskaya (Brest)|Bretagne|Bridgetown|British Columbia|Brnensky|Brodsko-Posavska Zupanija|Brong-Ahafo|Brunei and Muara|Brussels Capitol Region|Bubanza|Budapest|Budejovicky|Buenos Aires|Buenos Aires Capital Federal|Bujumbura|Bulqize|Bumthang|Bur Sa'id|Burgas|Burgenland|Bururi|Cabanas|Cabinda|Cabrayil Rayonu|Cacheu|Cairo|Caldas|Calilabad Rayonu|Camaguey|Canar|Canillo|Cankuzo|Caqueta|Carchi|Carriacou and Petit Martinique|Cartago|Casanare|Castel|Cat Island|Catamarca|Cauca|Cayo|Ceara|Central|Central|Central|Central River|Centre|Centre|Centre|Centro Sur|Cesar|Ch'khorotsqus|Ch'okhatauris|Chaco|Chalatenango|Champagne-Ardenne|Chandigarh|Chandpur|Chari-Baguirmi|Chhattisgarh|Chhukha|Chiat'ura|Chimaltenango|Chimborazo|Chin State|Chiquimula|Chirang|Chittagong|Chlef|Chobe|Choco|Choluteca|Chongqing|Christ Church|Christmas Island|Chuadanga|Chubut|Chuquisaca|Cibitoke|Ciego de Avila|Cienfuegos|Ciudad de La Habana|Clipperton Island|Cochabamba|Collines|Colon|Comayagua|Comilla|Comoe|Conakry|Constantine|Copan|Coquimbo|Cordoba|Cordoba|Corozal|Corrientes|Corse|Cortes|Cotopaxi|Couffo|Cox's Bazar|Coyah|Creek|Csongrad|Cuando Cubango|Cuanza Norte|Cuanza Sul|Cundinamarca|Cunene|Cuscatlan|Cuvette|Cuyuni-Mazaruni|Dabakala|Dabola|Dabon|Dadra and Nagar Haveli|Daga|Dajabon|Dalaba|Dalasysla|Daloa|Daman and Diu|Damascus|Danane|Daoukro|Daskasan Rayonu|Davaci Rayonu|Debrecen|Dedop'listsqaros|Delhi|Delvine|Demerara-Mahaica|Denkel|Devoll (Bilisht)|Devonshire|Dhaka|Dhodhekanisos|Diber (Peshkopi)|Dikhil|Dimbokro|Dinajpur|Dinguiraye|Dire Dawa|Direction Island|Distrito Capital de Santa Fe de Bogota|Distrito Federal|Distrito Nacional|Divo|Djelfa|Djibouti|Dmanisis|Dobrich|Doha|Domoni|Donga|Drama|Duarte|Dubreka|Dubrovacko-Neretvanska Zupanija|Duekoue|Dumyat|Dunaujvaros|Durres|Dushet'is|East Berbice-Corentyne|Eastern|Eastern|Eastern|Eastern|Eger|El Bayadh|El Oro|El Oued|El Paraiso|El Progreso|El Seibo|El Tarf|Elbasan|Elias Pina|Encamp|Entre Rios|Equateur|Escaldes-Engordany|Escuintla|Esmeraldas|Espaillat|Espirito Santo|Essequibo Islands-West Demerara|Est|Estuaire|Etela-Suomen Laani|Europa Island |Evritania|Evros|Evvoia|Extreme-Nord|Exuma|Eyjafjardharsysla|Eysturoy|Falkland Islands (Islas Malvinas) |Famagusta|Farah|Faranah|Faridpur|Faryab|Federation of Bosnia and Herzegovina|Fejer|Feni|Ferkessedougou|Fier|Florina|Fokis|Fomboni|Forecariah|Forest|Formosa|Franche-Comte|Francisco Morazan|Francistown|Fredericksberg|Frederiksborg|Freeport|French Guiana |Fresh Creek|Fria|Fthiotis|Fujian|Fuzuli Rayonu|Fyn|Gaborone|Gabrovo|Gabu|Gadabay Rayonu|Gagnoa|Gaibandha|Galapagos|Gambela Hizboch|Ganca Sahari|Ganzourgou|Gaoual|Gardabanis|Gaza Strip |Gazipur|Geghark'unik'|Geylegphug|Ghanzi|Ghardaia|Ghazni|Ghowr|Gibraltar |Gitega|Gjirokaster|Gnagna|Goa|Goias|Gopalganj|Goranboy Rayonu|Gori|Goris|Gourma|Governor's Harbour|Goycay Rayonu|Gracias a Dios|Gramsh|Grand Bassam|Grand'Anse|Grand-Lahou|Grande Comore (Njazidja)|Grande-Terre|Granma|Greater Accra|Green Turtle Cay|Grevena|Gribingui|Guainia|Guam |Guanacaste|Guangdong|Guangxi|Guantanamo|Guatemala|Guaviare|Guayas|Gueckedou|Guelma|Guera|Guiglo|Guizhou|Gujarat|Gullbringusysla|Gurjaanis|Gyor|Gyor-Moson-Sopron|Ha|Habiganj|Haciqabul Rayonu|Hafnarfjordhur|Hainan|Hainaut|Hajdu-Bihar|Hamasen|Hamburg|Hamilton|Hamilton|Harbour Island|Hareri Hizb|Harjumaa (Tallinn)|Haryana|Has (Krume)|Hato Mayor|Haut-Mbomou|Haut-Ogooue|Haute-Kotto|Haute-Normandie|Haute-Sangha|Heard Island and McDonald Islands |Hebei|Heilongjiang|Helmand|Henan|Herat|Heredia|Hessen|Heves|High Rock|Hiiumaa (Kardla)|Himachal Pradesh|Hodmezovasarhely|Holguin|Holy See (Vatican City|Home Island|Homyel'skaya (Homyel')|Hong Kong|Horad Minsk|Horsburgh Island|Houet|Howland Island|Hrodzyenskaya (Hrodna)|Huambo|Hubei|Huehuetenango|Huila|Huila|Hunan|Husavik|Ida-Virumaa (Johvi)|Ile Crozet|Ile Glorieuse |Ile du Lys|Ile-de-France|Iles Kerguelen|Iles Saint-Paul et Amsterdam |Iles Sous-le-Vent |Iles de la Petite Terre|Iles des Saintes|Iles du Vent|Ilia|Illizi|Imathia|Imbabura|Imisli Rayonu|Inagua|Independencia|Intibuca|Ioannina|Ioba|Irakleion|Irbid|Isafjordhur|Isla de la Juventud|Islas de la Bahia|Issia|Istarska Zupanija|Ita-Suomen Laani|Izabal|Jacqueville|Jaipurhat|Jalapa|Jamalpur|Jammu and Kashmir|Janub Sina'|Jarvamaa (Paide)|Jasz-Nagykun-Szolnok|Javis|Jedah|Jerusalem|Jessore|Jhalokati|Jharkhand|Jhenaidah|Jiangsu|Jiangxi|Jidd Hafs|Jihlavsky|Jijel|Jilin|Jogevamaa (Jogeva)|Jost Van Dyke|Jowzjan|Jujuy|Jutiapa|Juzur Hawar|K'arelis|K'ut'aisi|Kabol|Kachin State|Kadiogo|Kaernten|Kafr ash Shaykh|Kalbacar Rayonu|Kampong Cham|Kampong Chhnang|Kampong Spoe|Kampong Thum|Kampot|Kandahar|Kandal|Kanem|Kankan|Kaoh Kong|Kapisa|Kaposvar|Karak|Kardhitsa|Karlovacka Zupanija|Karlovarsky|Karnataka|Karuzi|Kasai-Occidental|Kasai-Oriental|Kaspis|Kastoria|Katanga|Katiola|Kavaje|Kavala|Kayah State|Kayanza|Kayin State|Keb|Kecskemet|Kefallinia|Keflavik|Kemo-Gribingui|Kemps Bay|Kenedougou|Kerala|Kerkyra|Kerouane|Kgalagadi|Kgatleng|Khagrachari|Khalkidhiki|Khania|Kharagaulis|Khashuris|Khaskovo|Khenchela|Khios|Khobis|Khonis|Khulna|Kie-Ntem|Kikladhes|Kilkis|Kindia|Kinshasa|Kirundo|Kishoreganj|Kissidougou|Kitaa (Vestgronland)|Kjosarsysla|Kobenhavn|Kobenhavns|Kolonje (Erseke)|Komandjari|Komarom-Esztergom|Kompienga|Konar|Kondoz|Kopavogur|Koprivnicko-Krizevacka Zupanija|Korce|Korhogo|Korinthia|Kossi|Kotayk'|Koubia|Kouilou|Koundara|Koupelogo|Kouritenga|Kouroussa|Kourweogo|Kozani|Kracheh|Kralovehradecky|Krapinsko-Zagorska Zupanija|Kruje|Kucove|Kukes|Kurbin|Kurdamir Rayonu|Kurdzhali|Kurigram|Kushtia|Kuwait|Kweneng|Kyrenia|Kyustendil|La Altagracia|La Guajira|La Habana|La Libertad|La Massana|La Pampa|La Paz|La Paz|La Paz|La Rioja|La Romana|La Union|La Vega|Laane-Virumaa (Rakvere)|Laanemaa (Haapsalu)|Labe|Lac|Lacin Rayonu|Laghman|Laghouat|Lagodekhis|Lakonia|Lakota|Lakshadweep|Lakshmipur|Lalmonirhat|Lanch'khut'is|Languedoc-Roussillon|Lankaran Rayonu|Lankaran Sahari|Lansi-Suomen Laani|Lappi|Larisa|Larnaca|Las Tunas|Lasithi|Lekoumou|Lelouma|Lempira|Lentekhis|Leraba|Lerik Rayonu|Lesvos|Levkas|Lezhe|Lhuntshi|Liaoning|Liberecky|Libertador General Bernardo O'Higgins|Librazhd|Licko-Senjska Zupanija|Liege|Likouala|Limassol|Limburg|Limon|Limousin|Litoral|Littoral|Littoral|Lobatse|Lobaye|Logone Occidental|Logone Oriental|Loja|Lola|Long Island|Loroum|Lorraine|Lorri|Los Lagos|Los Rios|Lovech|Lower River|Lowgar|Luanda|Lunda Norte|Lunda Sul|Lushnje|Luxembourg|M'Sila|Macenta|Madaripur|Madhya Pradesh|Madinat 'Isa|Madinat Hamad|Magallanes y de la Antartica Chilena|Magdalena|Magnisia|Magura|Magway|Mahaica-Berbice|Maharashtra|Mahilyowskaya (Mahilyow)|Maio|Makamba|Malanje|Malesi e Madhe (Koplik)|Mali|Mallakaster (Ballsh)|Mamou|Man|Manabi|Mandalay|Mandiana|Mangaia|Maniema|Manihiki|Manikganj|Manipur|Manitoba|Mankono|Manu'a|Manuae|Maranhao|Maria Trinidad Sanchez|Marie-Galante|Marneulis|Marsh Harbour|Martvilis|Masalli Rayonu|Mascara|Mat (Burrel)|Matanzas|Mato Grosso|Mato Grosso do Sul|Matruh|Mauke|Maule|Maulvi bazar|Mayaguana|Mayo-Kebbi|Mbahiakro|Mbomou|Mecca|Mecklenburg-Vorpommern|Medea|Medimurska Zupanija|Meghalaya|Meherpur|Mendoza|Messinia|Mestiis|Meta|Midi-Pyrenees|Midland|Mila|Minas Gerais|Mingacevir Sahari|Minskaya|Mirdite (Rreshen)|Misiones|Miskolc|Mitiaro|Mizoram|Moheli (Mwali)|Mon State|Mondol Kiri|Mongar|Mono|Monsenor Nouel|Montana|Monte Cristi|Monte Plata|Morazan|Morona-Santiago|Moroni|Mostaganem|Mosteiros|Mouhoun|Moutsamoudou |Moxico|Moyen-Chari|Moyen-Ogooue|Mts'khet'is|Munshiganj|Muramvya|Muscat|Muyinga|Mwaro|Mykines|Mymensingh|Myrasysla|Naama|Naftalan Sahari|Nagaland|Nagykanizsa|Nahouri|Namentenga|Namibe|Namur|Nana-Mambere|Nangarhar|Naogaon|Napo|Narail|Narayanganj|Narino|Narsingdi|Nassau Island|Natore|Naumbiel|Nawabganj|Naxcivan Muxtar Respublikasi|Nayala|Neftcala Rayonu|Nei Mongol|Neskaupstadhur|Netrokona|Neuquen|New Brunswick|New Providence|New South Wales|Newfoundland|Ngamiland|Ngounie|Ngozi|Niari|Nicholls Town and Berry Islands|Nicosia|Niederoesterreich|Niedersachsen|Nilphamari|Nimruz|Ningxia|Ninotsmindis|Noakhali|Nograd|Nord|Nord|Nord-Est|Nord-Kivu|Nord-Ouest|Nord-Ouest|Nord-Pas-de-Calais|Nordhur-Isafjardharsysla|Nordhur-Mulasys-la|Nordhur-Thingeyjarsysla|Nordjylland|Nordrhein-Westfalen|Norte de Santander|North Bank|North Keeling Island|North-East|Northern|Northern|Northern Territory|Northwest Territories|Nova Scotia|Nunavut|Nyanga|Nyiregyhaza|Nzerekore|Oberoesterreich|Obock|Ocotepeque|Odienne|Ogooue-Ivindo|Ogooue-Lolo|Ogooue-Maritime|Oguz Rayonu|Oio|Olafsfjordhur|Olancho|Olomoucky|Ombella-Mpoko|Onis|Ontario|Oost-Vlaanderen|Oran|Orange Walk|Ordino|Orellana|Orientale|Orissa|Oromiya|Oruro|Oruzgan|Osjecko-Baranjska Zupanija|Ostravsky|Otdar Mean Cheay|Ouaddai|Ouaka|Ouargla|Oubritenga|Oudalan|Oueme|Ouest|Ouest|Ouham|Ouham-Pende|Oulun Laani |Oum el Bouaghi|Oume|Ozurget'is|P'ot'i|Pabna|Paget|Pailin|Paktia|Paktika|Palmerston|Panchagarh|Pando|Paphos |Para|Paraiba|Parana|Pardubicky|Parnumaa (Parnu)|Paro|Parvan|Passore|Pastaza|Patuakhali|Paul|Pays de la Loire|Pazardzhik|Pecs|Pedernales|Pella|Pemagatsel|Pembroke|Penrhyn|Peqin|Peravia|Permet|Pernambuco|Pernik|Pest|Peten|Phnum Penh|Piaui|Picardie|Pichincha|Pieria|Pinar del Rio|Pirojpur|Pita|Plateau|Plateaux|Pleven|Plovdiv|Plzensky|Pogradec|Poitou-Charentes|Polvamaa (Polva)|Pomeroon-Supenaam|Pondicherry|Poni|Pool|Porto Novo|Potaro-Siparuni|Potosi|Pouthisat|Pozesko-Slavonska Zupanija|Praha|Praia|Preah Seihanu (Sihanoukville)|Preah Vihear|Preveza|Prey Veng|Primorsko-Goranska Zupanija|Prince Edward Island|Provence-Alpes-Cote d'Azur|Puerto Plata|Pukapuka|Puke|Punakha|Punjab|Puntarenas|Putumayo|Qabala Rayonu|Qax Rayonu|Qazax Rayonu|Qazbegis|Qina|Qinghai|Qobustan Rayonu|Quba Rayonu|Qubadli Rayonu|Quebec|Queensland|Quetzaltenango|Quiche|Quinara|Quindio|Qusar Rayonu|Qvarlis|Ragged Island|Rajasthan|Rajbari|Rajshahi|Rakahanga|Rakhine State|Rangamati|Rangarvallasysla|Rangpur|Raplamaa (Rapla)|Rarotonga|Razgrad|Redonda|Region Metropolitana (Santiago)|Relizane|Republika Srpska|Retalhuleu|Rethimni|Reykjavik|Rheinland-Pfalz|Rhone-Alpes |Ribe|Ribeira Grande|Ringkobing|Rio Grande do Norte|Rio Grande do Sul|Rio Negro|Rio de Janeiro|Risaralda|Riyadh|Rock Sound|Rodhopi|Rondonia|Roraima|Rose Island|Roskilde|Rotanah Kiri|Rotuma|Ruse|Rust'avi|Rutana|Ruyigi|Saaremaa (Kuessaare)|Saarland|Saatli Rayonu|Sabirabad Rayonu|Sacatepequez|Sach'kheris|Sachsen|Sachsen-Anhalt|Sagaing|Sagarejos|Sahil|Saida|Saint Andrew|Saint Andrew|Saint Andrew|Saint David|Saint David|Saint George|Saint George|Saint George|Saint George|Saint George|Saint Georges|Saint James|Saint John|Saint John|Saint John|Saint John|Saint Joseph|Saint Joseph|Saint Lucy|Saint Luke|Saint Mark|Saint Mark|Saint Mary|Saint Michael|Saint Patrick|Saint Patrick |Saint Paul|Saint Paul|Saint Peter|Saint Peter|Saint Peter |Saint Philip|Saint Philip|Saint Thomas|Sakassou|Saki Rayonu|Saki Sahari|Sal|Salamat|Salcedo|Salt|Salta|Salyan Rayonu|Salzburg|Samana|Samangan|Samaxi Rayonu|Samchi|Samdrup Jongkhar|Samentenga|Samkir Rayonu|Samos|Samtrediis|Samux Rayonu|San Andres y Providencia|San Cristobal|San Jose |San Juan|San Juan|San Luis|San Marcos|San Miguel|San Pedro de Macoris|San Salvador|San Salvador and Rum Cay|San Vicente|San-Pedro|Sanaa|Sanchez Ramirez|Sancti Spiritus|Sandoy|Sandy Point|Sandys|Sangha|Sangha |Sanguie|Sant Julia de Loria|Santa Ana|Santa Barbara|Santa Catarina|Santa Catarina|Santa Cruz|Santa Cruz|Santa Cruz|Santa Fe|Santa Rosa|Santander|Santiago|Santiago Rodriguez|Santiago de Cuba|Santiago del Estero|Sao Domingos|Sao Filipe|Sao Nicolau|Sao Paulo|Sao Vicente|Sar-e Pol|Sarande|Saskatchewan|Sassandra|Satkhira|Saudharkrokur|Schleswig-Holstein|Seguela|Selebi-Pikwe|Semhar|Senakis|Senhit|Seno|Seraye |Sergipe|Serrai|Setif|Seydhisfjordhur|Shaanxi|Shamal Sina'|Shan State|Shandong|Shanghai|Shanxi|Shariatpur|Shemgang|Sherpur|Shirak|Shkoder|Shumen|Sibensko-Kninska Zupanija|Sichuan|Sidi Bel Abbes|Siem Reab|Sighnaghis|Siglufjordhur|Siguiri|Sikkim|Silistra|Sinfra|Sirajganj|Sisacko-Moslavacka Zupanija|Sissili|Sitrah|Siyazan Rayonu|Skagafjardharsysla|Skikda|Skrapar (Corovode)|Skuvoy|Sliven|Smiths|Smolyan|Snaefellsnes-og Hnappadalssysla|Sofiya|Sofiya-Grad|Solola|Somogy|Sonderjylland|Sonsonate|Sopron|Soubre|Souk Ahras|Soum|Sourou|South Australia|South Island|South Town|South-East|Southampton|Southern|Splitsko-Dalmatinska Zupanija|Spot Bay|St. Andrew|St. Martin|St. Peter Port|St. Pierre du Bois|St. Sampson|St. Saviour|Stake Bay|Stann Creek|Stara Zagora|Steiermark|Stoeng Treng|Storstrom|Strandasysla|Stredocesky|Streymoy|Suchitepequez|Sucre|Sucumbios|Sud|Sud|Sud-Est |Sud-Kivu |Sud-Ouest|Sudhur-Mulasysla|Sudhur-Thingeyjarsysla|Suduroy|Suhaj |Sumale|Sumqayit Sahari|Sunamganj|Susa Rayonu|Susa Sahari|Suwarrow|Svay Rieng|Swains Island|Sylhet|Syunik'|Szabolcs-Szatmar-Bereg|Szeged|Szekesfehervar|Szolnok|Szombathely|T'bilisi|T'elavis|T'erjolis|T'et'ritsqaros|T'ianet'is|Tabou|Tadjoura |Takev|Takhar|Takutea |Tamanghasset|Tamil Nadu|Tanda|Tandjile|Tangail|Tanintharyi|Tapoa|Tarapaca|Tarija|Tarrafal|Tartar Rayonu|Tartumaa (Tartu)|Tashigang|Tasmania|Tatabanya|Tavush|Tebessa|Telimele|Temburong|Tepelene|Thakurgaon|Thesprotia|Thessaloniki|Thimphu|Thueringen |Tianjin|Tiaret|Tiassale|Tiebissou|Tierra del Fuego|Tigray|Tindouf|Tingrela|Tipaza|Tirane (Tirana)|Tirane (Tirana)|Tirol|Tissemsilt|Tizi Ouzou|Tlemcen|Tocantins|Toledo|Tolima|Tolna|Tombali |Tongsa|Torteval|Tortola|Totonicapan|Touba|Tougue|Toulepleu|Toumodi|Tovuz Rayonu|Tqibuli|Trikala|Tripura|Tropoje (Bajram Curri)|Ts'ageris|Tsalenjikhis|Tsalkis|Tsqaltubo|Tucuman|Tungurahua|Tunu (Ostgronland) |Turgovishte|Tutong|Tuy|Tvoroyri|Ucar Rayonu|Uige|Upper Demerara-Berbice|Upper East|Upper River|Upper Takutu-Upper Essequibo|Upper West|Ustecky|Usulutan |Uttar Pradesh|Uttaranchal|Vagar |Vakaga|Vale |Valgamaa (Valga)|Valle|Valle del Cauca|Valparaiso|Valverde|Vanis|Varazdinska Zupanija|Vardak|Varna|Vas|Vaupes|Vavoua|Vayots' Dzor|Vejle|Veliko Turnovo|Vestsjalland|Vesttmannaeyjar|Vestur-Bardhastrandarsysla|Vestur-Hunavatnssysla|Vestur-Isafjardharsysla|Vestur-Skaftafellssysla|Veszprem|Veszprem|Viborg |Vichada|Victoria|Vidin|Viljandimaa (Viljandi)|Villa Clara |Virgin Gorda|Viroviticko-Podravska Zupanija|Vitsyebskaya (Vitsyebsk)|Vlaams Brabant|Vlore|Voiotia|Volta|Vorarlberg|Vorumaa (Voru) |Vratsa|Vukovarsko-Srijemska Zupanija|Wangdi Phodrang|Warwick|Wele-Nzas |West Bengal|West End|West Island|West-Vlaanderen|Western|Western|Western |Western |Western |Western Australia|Wien|Woleu-Ntem |Xacmaz Rayonu|Xankandi Sahari|Xanlar Rayonu|Xanthi|Xinjiang|Xizang (Tibet)|Xizi Rayonu|Xocali Rayonu|Xocavand Rayonu|Yagha|Yambol|Yamoussoukro|Yangon|Yardimli Rayonu|Yatenga|YeDebub Biheroch Bihereseboch na Hizboch |Yerevan|Yevlax Rayonu|Yevlax Sahari|Yomou |Yoro|Yukon Territory|Yunnan|Zabol|Zacapa |Zadarska Zupanija|Zagreb|Zagrebacka Zupanija|Zaire|Zakinthos |Zala|Zalaegerszeg|Zamora-Chinchipe |Zangilan Rayonu|Zaqatala Rayonu|Zardab Rayonu|Zarqa|Zestap'onis|Zhejiang|Ziro|Zlinsky |Zondomo|Zou|Zoundweogo|Zuenoula |Zugdidi|Zugdidis"
        var cityArray = cityString.split("|").toTypedArray()

        v.address_view.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            //val addressArray = arrayOf("Amman","London")


            builder.setTitle("Select your address")
            builder.setSingleChoiceItems(cityArray, -1){ dialog: DialogInterface, which: Int ->
                v.address.hint = cityArray[which]
                opt = cityArray[which]

            }
            builder.setPositiveButton("OK"){dialog, which ->
                flag_address = true
                //database
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                v.address.hint = AppInfo.Address
                dialog.dismiss()
                flag_address = false
            }
            val dialog = builder.create()
            dialog.show()
        }


        v.save_changes.setOnClickListener {
            var flag_name = false
            var flag_pass = false
            var flag_male = false
            var flag_female = false


            if(v.account_name.text.toString()!= ""){
                flag_name = true
            }
            if(old_password.text.toString() != "") {
                if (v.new_password.text.toString() == v.confirm_password.text.toString()) {
                    if (v.new_password.text.length > 7)
                        flag_pass = true
                    else {
                        Toast.makeText(activity, "Insert at least 8 characters", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(activity, "Passwords don't match", Toast.LENGTH_LONG).show()
                }
            }
            if(opt == AppInfo.Address){
                flag_address = false
            }


            if (AppInfo.Gender == "0" && v.gender_toggle.isChecked){
                flag_male = true
            }
            else if (AppInfo.Gender == "1" && !v.gender_toggle.isChecked){
                flag_female = true
            }


            if(flag_name || flag_pass || flag_address || flag_male || flag_female){ //|| flag_male || flag_female
                val builder = android.app.AlertDialog.Builder(activity)
                builder.setTitle("Alert")
                builder.setMessage("Are you sure?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    // database update
                    var pd = ProgressDialog(activity)
                    pd.setMessage("Please Wait...")
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    pd.show()
                    var rq2 = Volley.newRequestQueue(activity)
                    var sr = object : StringRequest(
                        Request.Method.POST, AppInfo.web + "updateinfo.php",
                        Response.Listener { response ->
                            pd.hide()
                            if(response != "-1"){
                                var arr = response.split("/")
                                for ( i in 0 until arr.size){
                                    if(arr[i] == "name"){
                                        AppInfo.Name = v.account_name.text.toString()
                                        Toast.makeText(activity, "Name changed", Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "address"){
                                        AppInfo.Address = opt
                                        Toast.makeText(activity, "Address changed", Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "pass"){
                                        Toast.makeText(activity,"Password changed successfully",Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "gender"){
                                        Toast.makeText(activity,"Gender changed successfully",Toast.LENGTH_LONG).show()
                                        if(AppInfo.Gender == "0")
                                            AppInfo.Gender = "1"
                                        else{
                                            AppInfo.Gender = "0"
                                        }
                                    }
                                }

                                var i = Intent(activity, MainActivity::class.java)
                                startActivity(i)

                            }
                            else if(response == "0"){
                                Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(activity,"Incorrect password",Toast.LENGTH_LONG).show()
                            }

                        },
                        Response.ErrorListener { error ->
                            pd.hide()
                            Toast.makeText(
                                activity, error.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }) {
                        override fun getParams(): MutableMap<String, String> {
                            var map = HashMap<String, String>()
                            if(flag_name)
                                map.put("name", v.account_name.text.toString())
                            if(flag_address)
                                map.put("address", opt)
                            if(flag_pass) {
                                map.put("password", v.old_password.text.toString())
                                map.put("new", v.new_password.text.toString())
                            }
                            if(flag_male){
                                map.put("gender","1")
                            }
                            if(flag_female){
                                map.put("gender","0")
                            }
                            map.put("email",AppInfo.Email)

                            return map
                        }
                    }

                    rq2.add(sr)
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
            else{
                Toast.makeText(activity,"No changes detected", Toast.LENGTH_SHORT).show()
            }
        }

        v.cancel.setOnClickListener {
            var i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }
        return v
    }
}
