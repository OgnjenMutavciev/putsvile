const mongoose = require("mongoose")

const radnikSchema = new mongoose.Schema({
    naziv: {
        type: String,
        required: true,
        unique: true
    },
    rodjenje: {
        datum: String,
        mesto: String
    },
    ocena: String,
    radnoIskustvo: String,
    dodatneSposobnosti: String,
    cena: String,
    tipUsluge: String,
    avatar: { type: String, default: "default.png" }
}, { collection: "radnici" })

module.exports = mongoose.model("radnik", radnikSchema)