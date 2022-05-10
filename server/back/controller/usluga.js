const express = require("express")
const router = express()
const usluge = require("../model/usluga")

router.get("/usluge", async (req, res) => {
    try {
        const sveUsluge = await usluge.find()
        res.status(200).json({
            uspeh: true,
            usluge: sveUsluge
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.get("/usluge/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jednaUsluga = await usluge.findOne({"tip": `${id}`})
        res.status(200).json({
            uspeh: true,
            usluga: jednaUsluga
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.post("/usluge", async (req, res) => {
    try {
        const novaUsluga = new usluge(req.body)
        await novaUsluga.save()
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

router.delete("/usluge/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jednaUsluga = await usluge.findOne({"tip": `${id}`})
        await jednaUsluga.delete()
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