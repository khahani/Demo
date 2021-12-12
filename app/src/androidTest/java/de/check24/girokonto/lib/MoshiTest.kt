package de.check24.girokonto.lib

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.check24.girokonto.data.source.api.response.FirstPageResponse
import de.check24.girokonto.data.source.api.response.NullToZeroDoubleAdapter
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoshiTest {
    lateinit var moshi: Moshi
    lateinit var adapter: JsonAdapter<FirstPageResponse>

    @Before
    fun init() {
        moshi = Moshi.Builder()
            .add(NullToZeroDoubleAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
        adapter = moshi.adapter(FirstPageResponse::class.java)
    }

    @Test
    fun nullToZeroDoubleAdapter_null_zero() {
        val response = adapter.fromJson(
            "{\n" +
                    "    \"slogan\": {\n" +
                    "      \"titles\": [\n" +
                    "        \"schnell, einfach & sicher\",\n" +
                    "        \"In 30 minuten eroffnen und umziehen\",\n" +
                    "        \"Kostenlose Konten & bis zu 75 € Bonus\"\n" +
                    "      ],\n" +
                    "      \"logo\": \"https://www.check24.de/assets/images/mobile/misc/funktioniert_garantie_girokonto.png\"\n" +
                    "    },\n" +
                    "    \"recommends\": [\n" +
                    "      {\n" +
                    "        \"title\": \"Empfehlung bedingungslos kostenlose Kontoführung\",\n" +
                    "        \"text\": \"Das dauerhaft kostenlose Girokonto der DKB glänzt durch kostenlose Bargeldabhebung weltweit, eine kostenlose VISA Debitkarte sowie einen sehr attraktiven Dispozins. Zusätzlich kann man bargeldlos mit Apple und Google Pay bezahlen.\",\n" +
                    "        \"short\": \"DKB - Girokonto\",\n" +
                    "        \"image\": \"https://konten.c24static.de/accounts/static/banklogos/all/dkb.png\",\n" +
                    "        \"fee\": 0.0,\n" +
                    "        \"bonus\": null\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"title\": \"Empfehlung Exklusivaktion\",\n" +
                    "        \"text\": \"Das PlusKonto der HypoVereinsbank punktet durch kostenlose Kontoführung, kostenlose Kreditkarte, sehr niedrigen Dispozins von 2,66% und 75€ Bonus (bei Teilnahme am Vorteilsprogramm).\",\n" +
                    "        \"short\": \"HypoVereinsbank - HVB PlusKonto\",\n" +
                    "        \"image\": \"https://konten.c24static.de/accounts/static/banklogos/all/hypovereinsbank.png\",\n" +
                    "        \"fee\": 0.0,\n" +
                    "        \"bonus\": 75.0\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }"
        )

        assertThat(response!!.recommends[0].bonus, Matchers.equalTo(0.0))

    }
}