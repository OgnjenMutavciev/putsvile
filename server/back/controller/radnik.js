const express = require("express")
const router = express()
const multer = require("multer")
const upload = multer({ dest: "uploads/" })
const usluge = require("../model/usluga")
const radnici = require("../model/radnik")

router.get("/radnici", async (req, res) => {
    try {
        const sviRadnici = await radnici.find()
        res.status(200).json({
            uspeh: true,
            radnici: sviRadnici
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.get("/radnici/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jedanRadnik = await radnici.findOne({"naziv": `${id}`})
        res.status(200).json({
            uspeh: true,
            radnik: jedanRadnik
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.post("/radnici", upload.single("avatar"), async (req, res) => {
    try {
        const noviRadnik = new radnici(req.body)
        // noviRadnik.avatar = req.file.filename
        await noviRadnik.save()
        const jednaUsluga = await usluge.findOne({"tip": `${req.body.tipUsluge}`})
        if (jednaUsluga) {
            jednaUsluga.radnici.push(req.body.naziv)
            jednaUsluga.kolicina++
            await jednaUsluga.save()
        } else {
            const novaUsluga = new usluge({
                "tip": `${req.body.tipUsluge}`
            })
            novaUsluga.radnici.push(req.body.naziv)
            novaUsluga.kolicina++
            await novaUsluga.save()
        }
        res.status(200).json({
            uspeh: true
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.delete("/radnici/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jedanRadnik = await radnici.findOne({"naziv": `${id}`})
        const jednaUsluga = await usluge.findOne({"tip": `${jedanRadnik.tipUsluge}`})
        let radniciniz=[];
        jednaUsluga.radnici.forEach((radnik) => {
            if(radnik!=id) {
                radniciniz.push(radnik)
            }
        })
        jednaUsluga.radnici = radniciniz
        jednaUsluga.kolicina--
        if(jednaUsluga.kolicina > 0) {
            await jednaUsluga.save()
        } else {
            await jednaUsluga.delete()
        }
        await jedanRadnik.delete()
        res.status(200).json({
            uspeh: true
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

module.exports = router