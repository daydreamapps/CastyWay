package com.dda.castyway.shared.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("rss")
data class Rss(
    @XmlElement(true)
    val channel: Channel
)

@Serializable
@XmlSerialName("channel")
data class Channel(
    @XmlElement(true)
    val title: String,
    @XmlElement(true)
    val link: String,
    @XmlElement(true)
    val description: String,
    @XmlElement(true)
    @XmlSerialName("item")
    val items: List<Item>
)

@Serializable
@XmlSerialName("item")
data class Item(
    @XmlElement(true)
    val title: String,
    @XmlElement(true)
    val pubDate: String,
    @XmlElement(true)
    val link: String,
    @XmlElement(true)
    val description: String,
    @XmlElement(true)
    @XmlSerialName("duration", "http://www.itunes.com/dtds/podcast-1.0.dtd", "itunes")
    val duration: String
)
