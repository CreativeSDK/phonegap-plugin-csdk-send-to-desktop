/* globals require */

/*!
 * Module dependencies.
 */

 var cordova = require('./helper/cordova'),
    document = require('./helper/document'),
    CSDKSendToDesktop = require('../www/SendToDesktop'),
    execSpy,
    execWin,
    options;

/*!
 * Specification.
 */

describe('phonegap-plugin-csdk-send-to-desktop', function () {
    beforeEach(function () {
        execWin = jasmine.createSpy();
        execSpy = spyOn(cordova.required, 'cordova/exec').andCallFake(execWin);
    });

    describe('Send To Desktop', function () {
        it('should exist', function () {
            expect(CSDKSendToDesktop).toBeDefined();
            expect(typeof CSDKSendToDesktop === 'object').toBe(true);
        });

        it('should contain a send function', function () {
            expect(CSDKSendToDesktop.send).toBeDefined();
            expect(typeof CSDKSendToDesktop.send === 'function').toBe(true);
        });
    });

    describe('App Type', function() {
        it('should contain the AppType constants', function () {
            expect(CSDKSendToDesktop.AppType.PHOTOSHOP).toBe(0);
            expect(CSDKSendToDesktop.AppType.INDESIGN).toBe(1);
            expect(CSDKSendToDesktop.AppType.ILLUSTRATOR).toBe(2);
        });
    });

    describe('Absolute Path', function() {
        it('should contain a getAbsolutePath function', function () {
            expect(CSDKSendToDesktop.getAbsolutePath).toBeDefined();
            expect(typeof CSDKSendToDesktop.getAbsolutePath === 'function').toBe(true);
        });

        it('should be valid', function() {
            expect(CSDKSendToDesktop.getAbsolutePath('http://www.here.com/index.html', 'http://www.here.com/img/logo.png')).toBe('http://www.here.com/img/logo.png');
            expect(CSDKSendToDesktop.getAbsolutePath('http://www.here.com/contacts/index.html', '../img/logo.png')).toBe('http://www.here.com/img/logo.png');
            expect(CSDKSendToDesktop.getAbsolutePath('http://www.here.com/index.html', './img/logo.png')).toBe('http://www.here.com/img/logo.png');
            expect(CSDKSendToDesktop.getAbsolutePath('file:///android_asset/www/index.html', 'file:///android_asset/www/img/logo.png')).toBe('file:///android_asset/www/img/logo.png');
            expect(CSDKSendToDesktop.getAbsolutePath('file:///android_asset/www/index.html', '../www/img/logo.png')).toBe('file:///android_asset/www/img/logo.png');
            expect(CSDKSendToDesktop.getAbsolutePath('file:///android_asset/www/index.html', './img/logo.png')).toBe('file:///android_asset/www/img/logo.png');
        });
    });

    describe('CSDKSendToDesktop instance', function () {
        describe('cordova.exec', function () {
            it('should call cordova.exec on next process tick', function (done) {
                CSDKSendToDesktop.send(function(profile) {
                }, function() {}, '', CSDKSendToDesktop.AppType.PHOTOSHOP, '');
                setTimeout(function () {
                    expect(execSpy).toHaveBeenCalledWith(
                        jasmine.any(Function),
                        jasmine.any(Function),
                        'CSDKSendToDesktop',
                        'send',
                        jasmine.any(Object)
                    );
                    done();
                }, 100);
            });
        });
    });
});
