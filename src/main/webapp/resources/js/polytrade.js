/**
 * @author hedi brahim <hedi.brahim@gmail.com>
 * version: 1.0.0
 * https://github.com/hedibrahim/polytrade/
 */

function enMillimes(value) {
	var v = value*1.18;
    return v.toFixed(3).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
}
	